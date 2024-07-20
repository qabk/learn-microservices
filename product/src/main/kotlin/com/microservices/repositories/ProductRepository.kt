package com.microservices.repositories

import com.microservices.entities.ProductEntity
import org.springframework.data.mongodb.repository.MongoRepository


interface ProductRepository : MongoRepository<ProductEntity, String?> {

}