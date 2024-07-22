package com.microservices.customer.repositories

import com.microservices.customer.entities.CustomerEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface CustomerRepository : MongoRepository<CustomerEntity, String?> {
}