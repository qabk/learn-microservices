package com.microservices.servicescommons.requests

import com.microservices.servicescommons.constants.PaymentStatus


data class PaymentRequest (
    val amount: Long?,
    val currency: String?,
    val status: PaymentStatus?,
    val orderId: String?,
)