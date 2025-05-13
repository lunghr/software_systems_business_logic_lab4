package service

import com.sendgrid.*
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Email
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import com.sendgrid.helpers.mail.objects.Personalization


@Service
class EmailService(
    private val sendGridApiKey: String = ""
) {

    fun sendCreatedOrderEmail(toEmail: String, orderId: String, totalPrice: Double, paymentWindow: String) {
        val templateId = "d-1a3ff013ea4a4583ae6ef4166ccff0e0"
        val dynamicData = mapOf(
            "order_id" to orderId,
            "total_price" to totalPrice.toString(),
            "payment_window" to paymentWindow
        )
        sendEmail(toEmail, templateId, dynamicData)
    }

    fun sendPaidOrderEmail(toEmail: String, orderId: String) {
        val templateId = "d-fda64314066c4d62a1d147e3bd8d17a4"
        val dynamicData = mapOf(
            "order_id" to orderId
        )
        sendEmail(toEmail, templateId, dynamicData)
    }

    fun sendCancelledOrderEmail(toEmail: String) {
        val templateId = "d-308b5c4bcda24d89b2545af9230634e5"
        val dynamicData = mapOf(
            "email" to toEmail,
        )
        sendEmail(toEmail, templateId, dynamicData)
    }

    private fun sendEmail(toEmail: String, templateId: String, dynamicData: Map<String, Any>) {
        val from = Email("myilanka8@gmail.com")
        val to = Email(toEmail)


        val personalization = Personalization().apply {
            addTo(to)
            dynamicData.forEach { (k, v) -> addDynamicTemplateData(k, v) }
        }

        val mail = Mail().apply {
            setFrom(from)
            setTemplateId(templateId)
            addPersonalization(personalization)
        }

        val sg = SendGrid(sendGridApiKey)
        val request = Request().apply {
            method = Method.POST
            endpoint = "mail/send"
            body = mail.build()
        }

        val response = sg.api(request)
        println("✅ Email sending status: ${response.statusCode}")
    }


}

