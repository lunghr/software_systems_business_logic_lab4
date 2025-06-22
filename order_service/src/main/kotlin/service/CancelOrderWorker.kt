package service

import com.example.service.OrderService
import jakarta.annotation.PostConstruct
import org.camunda.bpm.client.ExternalTaskClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class CancelOrderWorker(
    @Value("\${camunda.rest.url}") private val camundaRest: String,
    private val orderService: OrderService
) {

    private lateinit var client: ExternalTaskClient   // держим ссылку!

    @PostConstruct
    fun subscribe() {

        client = ExternalTaskClient.create()          // инициализация
            .workerId("order-service")                // явное имя воркера
            .baseUrl(camundaRest)                     // http://camunda:8080/…
            .asyncResponseTimeout(30_000)
            .build()

        client.subscribe("cancel-order")
//            .tenantIdIn("cancel-order")             // один tenant
            .lockDuration(30_000)
            .variables("orderId","userEmail")
            .handler { task, svc ->
                println("📩 task ${task.id}")
                val orderId   = task.getVariable<String>("orderId")
                val userEmail = task.getVariable<String>("userEmail")
                println("📋 orderId=$orderId  userEmail=$userEmail")
                orderService.cancelOrder(UUID.fromString(orderId), userEmail)
                svc.complete(task)
                println("✅ done ${task.id}")
            }

        println("🟢 External task client started, subscribed to cancel-order")
    }
}

