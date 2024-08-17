package com.microservices.servicescommons.requests

data class OrderRequest (
    val customerId: String?,
    val products: Map<String,Int>?,
    val isExecuted: Boolean
)
