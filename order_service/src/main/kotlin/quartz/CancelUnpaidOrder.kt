package com.example.quartz

import com.example.service.OrderService
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.stereotype.Component
import java.util.*

@Component
class CancelUnpaidOrder(
    private val orderService: OrderService
): Job {
    override fun execute(context: JobExecutionContext) {
        val orderId  = context.jobDetail.jobDataMap.getString("orderId")
        val userEmail = context.jobDetail.jobDataMap.getString("userEmail")
        orderService.cancelOrder(UUID.fromString(orderId), userEmail)
    }
}