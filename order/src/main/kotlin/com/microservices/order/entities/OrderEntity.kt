package com.microservices.order.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "order")
data class OrderEntity (
        @Id
        val id: String? = null,
        val products: Map<String, Int>?,
        val orderCustomer : String?,
        val orderTime: Instant? = Instant.now(),
        val isExecuted: Boolean?
)