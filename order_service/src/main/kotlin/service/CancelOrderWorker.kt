package service

import com.example.service.OrderService
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
@ExternalTaskSubscription(
    topicName = "cancel-order",          // ← правильное имя атрибута
    lockDuration = 30_000L,
    variableNames = ["orderId", "userEmail"] // ← …и здесь
)
class CancelOrderWorker(
    private val orderService: OrderService
) : ExternalTaskHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun execute(task: ExternalTask, service: ExternalTaskService) {
        try {
            val orderId = UUID.fromString(task.getVariable<String>("orderId"))
            val userEmail = task.getVariable<String>("userEmail")
            log.info("📩  task={} orderId={} userEmail={}", task.id, orderId, userEmail)

            orderService.cancelOrder(orderId, userEmail)
            service.complete(task)                          // <-- успех
            log.info("✅  completed task={}", task.id)
        } catch (ex: Exception) {
            log.error("❌  error in task={}, {}", task.id, ex.message, ex)
            // ⚠️  позиционные аргументы, без имён!
            service.handleFailure(
                task,
                ex.message ?: "unexpected error",
                ex.stackTraceToString(),
                3,            // retries
                5_000L        // retryTimeout in ms
            )
        }
    }
}