package com.example.software_systems_business_logic_lab1.application.models

import com.example.software_systems_business_logic_lab1.application.models.enums.OrderStatus
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID


@Entity
@Table(name = "orders")
class Order(
    @Id
    @Column(name = "order_id")
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(name = "created_at")
    @JsonFormat(pattern = "HH:mm dd.MM.yy ")
    var createdAt: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yy")),

    @Column(name = "order_status")
    var orderStatus: OrderStatus = OrderStatus.WAITING_FOR_PAYMENT,

    @Column(name = "total_price")
    var totalPrice: Double
)
