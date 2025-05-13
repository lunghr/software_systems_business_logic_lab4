package com.example.service

import com.example.quartz.CancelUnpaidOrder
import org.quartz.JobBuilder
import org.quartz.Scheduler
import org.quartz.TriggerBuilder
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderSchedulerService(
    private val scheduler: Scheduler
) {

    fun scheduledOrderCancellation(orderId: UUID, userEmail: String) {
        val jobDetail = JobBuilder.newJob(CancelUnpaidOrder::class.java)
            .withIdentity(orderId.toString())
            .usingJobData("orderId", orderId.toString())
            .usingJobData("userEmail", userEmail)
            .build()

        val trigger = TriggerBuilder.newTrigger()
            .withIdentity("cancel-trigger-$orderId", "orders")
//            .startAt(Date(System.currentTimeMillis() + (0.1 * 60 * 1000).toLong()))
            .startAt(Date(System.currentTimeMillis() + (1 * 60 * 1000).toLong()))
            .build()

        scheduler.scheduleJob(jobDetail, trigger)
    }
}