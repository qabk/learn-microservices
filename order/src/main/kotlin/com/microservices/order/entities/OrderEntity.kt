package com.microservices.order.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "order")
data class OrderEntity (
        @Id
        val id: String?,
        val customerId: String?
)