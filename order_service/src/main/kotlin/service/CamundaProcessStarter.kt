package service

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.util.*

@Service
class CamundaProcessStarter(
    @Value("\${camunda.rest.url}")            // http://camunda:8080/engine-rest
    private val camundaRestUrl: String,
    private val webClientBuilder: WebClient.Builder
) {
    private val webClient by lazy { webClientBuilder.baseUrl(camundaRestUrl).build() }

    fun startOrderCancellation(orderId: UUID, userEmail: String) {
        val body = mapOf(
            "variables" to mapOf(
                "orderId" to mapOf("value" to orderId, "type" to "String"),
                "userEmail" to mapOf("value" to userEmail, "type" to "String")
            )
        )

        try {
            // Сначала проверим, существует ли процесс с таким ключом
            val processDefinition = webClient.get()
                .uri("/process-definition?key=order-close")
                .retrieve()
                .bodyToMono(String::class.java)
                .block()

            println("Найденные определения процесса: $processDefinition")

            // Затем запускаем процесс
            // Затем запускаем процесс с учетом tenant ID
            webClient.post()
                .uri("/process-definition/key/order-close/tenant-id/cancel-order/start")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Void::class.java)
                .block()
            println("✅ Процесс отмены заказа $orderId успешно запущен")
        } catch (e: Exception) {
            val errorMessage = "Ошибка при запуске процесса отмены заказа $orderId: ${e.message}"
            println(errorMessage)
            println("URL запроса: $camundaRestUrl/process-definition/key/order-close/start")
            println("Тело запроса: $body")
            throw RuntimeException(errorMessage, e)
        }
    }
}
