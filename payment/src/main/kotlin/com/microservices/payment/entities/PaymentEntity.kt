package com.microservices.payment.entities

import com.microservices.servicescommons.constants.PaymentStatus
import jakarta.persistence.*


@Entity
@Table(name = "paymentdb")
data class PaymentEntity (
    @Id
    @SequenceGenerator(name = "payment_sequence", sequenceName = "payment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_sequence")
    val id: Long? = 0L,
    @Column(name = "amount")
    val amount: Long?,
    @Column(name = "currency")
    val currency: String?,
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    val status: PaymentStatus?,
    @Column(name = "orderId")
    val orderId: String?,
)