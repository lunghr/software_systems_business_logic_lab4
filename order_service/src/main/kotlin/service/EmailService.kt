package service

import com.example.kafka.KafkaServiceConsumer
import com.example.kafka.KafkaServiceProducer
import com.example.kafka.ResponseStorage
import com.sendgrid.*
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Email
import org.springframework.stereotype.Service
import com.sendgrid.helpers.mail.objects.Personalization
import java.util.UUID


@Service
class EmailService(
    private val kafkaServiceProducer: KafkaServiceProducer,
    private val responseStorage: ResponseStorage
) {

    fun sendCreatedOrderEmail(toEmail: String, orderId: String, totalPrice: Double, paymentWindow: String) {
        val correlationID = UUID.randomUUID()
        kafkaServiceProducer.getCreateTemplate(correlationID)

        val templateId = responseStorage.waitForResponse(
            correlationID.toString(),
            5,
            String::class.java
        ) ?: throw RuntimeException("Failed to get Create Template ID")

        val dynamicData = mapOf(
            "order_id" to orderId,
            "total_price" to totalPrice.toString(),
            "payment_window" to paymentWindow
        )
        sendEmail(toEmail, templateId, dynamicData)
    }

    fun sendPaidOrderEmail(toEmail: String, orderId: String) {
        val correlationID = UUID.randomUUID()
        kafkaServiceProducer.getPaidTemplate(correlationID)

        val templateId = responseStorage.waitForResponse(
            correlationID.toString(),
            5,
            String::class.java
        ) ?: throw RuntimeException("Failed to get Paid Template ID")


        val dynamicData = mapOf(
            "order_id" to orderId
        )
        sendEmail(toEmail, templateId, dynamicData)
    }

    fun sendCancelledOrderEmail(toEmail: String) {
        val correlationID = UUID.randomUUID()
        kafkaServiceProducer.getCancelledTemplate(correlationID)
        val templateId = responseStorage.waitForResponse(
            correlationID.toString(),
            5,
            String::class.java
        ) ?: throw RuntimeException("Failed to get Cancelled Template ID")

        val dynamicData = mapOf(
            "email" to toEmail,
        )
        sendEmail(toEmail, templateId, dynamicData)
    }

    private fun sendEmail(toEmail: String, templateId: String, dynamicData: Map<String, Any>) {
        val from = Email("myilanka8@gmail.com")
        val to = Email(toEmail)
        val correlationID = UUID.randomUUID()
        kafkaServiceProducer.getApiKey(correlationID)

        val sendGridApiKey = responseStorage.waitForResponse(
            correlationID.toString(),
            5,
            String::class.java
        ) ?: throw RuntimeException("Failed to get SendGrid API key")


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

