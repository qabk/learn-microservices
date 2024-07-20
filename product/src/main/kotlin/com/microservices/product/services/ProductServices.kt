package com.microservices.product.services

import com.microservices.product.dtos.requests.ProductRequest
import com.microservices.product.entities.ProductEntity
import com.microservices.product.repositories.ProductRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class ProductServices (private val productRepository: ProductRepository){
    fun createProduct(product: ProductRequest){
        productRepository.save(product.toEntity())
    }

    private fun ProductRequest.toEntity() : ProductEntity {
        return ProductEntity(
                name = this.name,
                description = this.description,
                price = this.price,
                validFrom = this.validFrom,
                validTo = this.validTo,
                currency = this.currency,
        )
    }
}