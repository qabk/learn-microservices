package com.microservices.product.repositories

import com.microservices.product.entities.ProductEntity
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.PostMapping


@Repository
interface ProductRepository : MongoRepository<ProductEntity, String?> {

}