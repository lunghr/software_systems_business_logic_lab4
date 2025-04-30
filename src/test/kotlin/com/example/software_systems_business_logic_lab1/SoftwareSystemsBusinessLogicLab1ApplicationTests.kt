package com.example.software_systems_business_logic_lab1

import com.example.software_systems_business_logic_lab1.application.dto.ProductDto
import com.example.software_systems_business_logic_lab1.application.models.*
import com.example.software_systems_business_logic_lab1.application.models.enums.OrderPaymentStatus
import com.example.software_systems_business_logic_lab1.application.models.enums.UserRole
import com.example.software_systems_business_logic_lab1.application.repos.OrderRepository
import com.example.software_systems_business_logic_lab1.application.services.*
import com.example.software_systems_business_logic_lab1.payment.bank.models.BankAccount
import com.example.software_systems_business_logic_lab1.payment.bank.models.Card
import com.example.software_systems_business_logic_lab1.payment.bank.repos.BankAccountRepository
import com.example.software_systems_business_logic_lab1.payment.bank.repos.CardRepository
import com.example.software_systems_business_logic_lab1.payment.bank.services.BankService
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.PaymentMethod
import com.example.software_systems_business_logic_lab1.payment.ozon_client.models.enums.TransactionStatus
import com.example.software_systems_business_logic_lab1.payment.ozon_client.repos.PaymentTransactionRepository
import com.example.software_systems_business_logic_lab1.payment.ozon_client.services.PaymentMethodService
import com.example.software_systems_business_logic_lab1.payment.ozon_client.services.TransactionService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@SpringBootTest
class SoftwareSystemsBusinessLogicLab1ApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var categoryService: CategoryService

    @Autowired
    private lateinit var cartService: CartService

    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var paymentMethodService: PaymentMethodService

    @Autowired
    lateinit var transactionService: TransactionService

    @Autowired
    lateinit var orderService: OrderService

    @Autowired
    lateinit var transactionRepository: PaymentTransactionRepository

    @Autowired
    lateinit var orderRepository: OrderRepository

    @SpyBean
    lateinit var warehouseService: WarehouseService

    @SpyBean
    lateinit var bankService: BankService

    @Autowired
    lateinit var bankAccountRepository: BankAccountRepository

    @Autowired
    lateinit var cardRepository: CardRepository

    lateinit var paymentMethod: PaymentMethod
    lateinit var order: Order
    lateinit var testUser: User
    lateinit var testCategory: Category
    lateinit var testProduct: Product
    lateinit var cart: Cart
    lateinit var card: Card

    @BeforeEach
    fun setup() {
        testUser = userService.createUser(
            User(
                username = "test",
                password = "test",
                email = "test",
                phoneNumber = "test",
                role = UserRole.USER
            )
        )
        testCategory = categoryService.createPaternalCategory("Test")
        testProduct = productService.createProduct(
            ProductDto(
                name = "testProduct",
                description = "test",
                price = 2.0,
                stockQuantity = 10,
                categoryId = testCategory.key.id
            )
        )
        cart = cartService.getCartByUserId(testUser)
        cartService.addProductToCart(cart.id, testProduct.key.productId)
        order = orderService.createOrder(cart.id, listOf(testProduct.key.productId))
        val bankAccount = bankAccountRepository.save(BankAccount())
        card = cardRepository.save(Card(bankAccount = bankAccount))
        paymentMethod =
            paymentMethodService.addNewPaymentMethod(card.cardNumber, card.cvv, card.expirationDate, testUser.id)
    }

    @AfterEach
    fun clean() {
        runCatching { paymentMethodService.deleteMethodById(paymentMethod.id) }
        runCatching { orderRepository.delete(order) }
        runCatching { cartService.delete(cart) }
        runCatching { bankService.deleteCard(card) }
        runCatching { productService.delete(testProduct) }
        runCatching { categoryService.delete(testCategory) }
        runCatching { userService.delete(testUser) }
    }

    @Test
    fun `should rollback all changes when warehouse throws exception`() {
        Mockito.doReturn(TransactionStatus.COMPLETED)
            .`when`(bankService).processTransaction(any(), any())

        Mockito.doThrow(RuntimeException("Warehouse failure"))
            .`when`(warehouseService).decreaseStockQuantity(order)

        val txs = transactionRepository.findAll()
        val updatedOrder = orderService.getOrderById(order.id)

        assertTrue(txs.isEmpty(), "Transaction should not be saved")
        assertEquals(OrderPaymentStatus.WAITING_FOR_PAYMENT, updatedOrder?.orderPaymentStatus)
    }
}


