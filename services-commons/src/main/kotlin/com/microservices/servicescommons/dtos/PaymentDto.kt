package com.microservices.servicescommons.dtos

import com.microservices.servicescommons.constants.PaymentStatus
import java.math.BigDecimal

data class PaymentDto (
    val id: Long?,
    val amount: Long?,
    val currency: String?,
    val status: PaymentStatus?,
    val orderId: String?,
)