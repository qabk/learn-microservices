package com.microservices.servicescommons.dtos

data class OrderDto (
    val id: String?,
    val customerDto: String?,
    val  products: Map<String,Int>?,
    val orderTime: String?,
    val isExecuted: Boolean,
)