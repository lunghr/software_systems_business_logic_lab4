package com.example.software_systems_business_logic_lab1.payment.bank.services

import com.example.software_systems_business_logic_lab1.payment.bank.models.BankAccount
import com.example.software_systems_business_logic_lab1.payment.bank.models.Card
import com.example.software_systems_business_logic_lab1.payment.bank.repos.BankAccountRepository
import com.example.software_systems_business_logic_lab1.payment.bank.repos.CardRepository
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.OzonPaymentData
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.PaymentTransaction
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.enums.TransactionStatus
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class BankService(
    private val cardRepository: CardRepository,
    private val bankAccountRepository: BankAccountRepository
) {

    fun createBankAccount(): BankAccount =
        try {
            bankAccountRepository.save(BankAccount())
        } catch (e: Exception) {
            throw RuntimeException("Failed to create bank account: ${e.message}")
        }

    fun createCard(accountNumber: String): Card =
        try {
            cardRepository.save(Card(bankAccount = bankAccountRepository.findByAccountNumber(accountNumber)))
        } catch (e: Exception) {
            throw RuntimeException("Failed to create card: ${e.message}")
        }

    fun validateCard(cardNumber: String, expirationDate: String, cvv: String): Boolean {
        return cardRepository.findByCardDetails(
            cardNumber = cardNumber,
            expirationDate = expirationDate,
            cvv = cvv
        )?.let {
            true
        } ?: false
    }

    fun topUpBankAccount(cardNumber: String, amount: Double): Double {
        val acc = bankAccountRepository.findAccountByCardNumber(cardNumber)
            ?: throw RuntimeException("Bank account not found for card number: $cardNumber")
        return bankAccountRepository.save(acc.copy(balance = acc.balance + amount)).balance
    }

    @Transactional
    fun processTransaction(paymentData: OzonPaymentData, transactionAmount: Double): TransactionStatus {
        require(
            validateCard(
                paymentData.cardNumber,
                paymentData.expirationDate,
                paymentData.cvv
            )
        ) { "Invalid card details" }
        val bankAccount = bankAccountRepository.findAccountByCardNumber(paymentData.cardNumber)
            ?: throw RuntimeException("Bank account not found for card number: ${paymentData.cardNumber}")
        if (bankAccount.balance < transactionAmount) {
            return TransactionStatus.FAILED
        }
        bankAccount.balance -= transactionAmount
        bankAccountRepository.save(bankAccount)
        return TransactionStatus.COMPLETED
    }

}