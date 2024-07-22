package com.microservices.product.services

import com.microservices.product.entities.ProductEntity
import com.microservices.product.repositories.ProductRepository
import com.microservices.servicescommons.dtos.ProductDto
import com.microservices.servicescommons.requests.ProductRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServices(private val productRepository: ProductRepository) {
    fun createProduct(product: ProductRequest) {
        productRepository.save(product.toEntity())
    }

    fun deleteProductById(id: String) {
        productRepository.deleteById(id)
    }

    fun findProductById(id: String): Optional<ProductDto> {
      return  productRepository.findById(id).map { it.toResponse() }
    }

    fun updateProduct(id: String,updateProduct: ProductRequest){
        val product = productRepository.findById(id).orElseThrow()
        productRepository.save(product.fromPatchRequest(updateProduct))
    }

    fun findAllProductByIds(ids: List<String>) :List<ProductDto>{
        return productRepository.findAllById(ids).map { it.toResponse() }
    }

    private fun ProductRequest.toEntity(): ProductEntity {
        return ProductEntity(
                name = this.name,
                description = this.description,
                price = this.price,
                validFrom = this.validFrom,
                validTo = this.validTo,
                currency = this.currency,
        )
    }

    private fun ProductEntity.fromPatchRequest(product: ProductRequest) : ProductEntity{
        return ProductEntity(
                id = this.id,
                name = product.name ?: this.name,
                description = product.description ?: this.description,
                price = product.price ?: this.price,
                validFrom = product.validFrom ?: this.validFrom,
                validTo = product.validTo ?: this.validTo,
                currency = product.currency ?: this.currency,
        )
    }

    private fun ProductEntity.toResponse(): ProductDto {
        return ProductDto(
                id = this.id,
                name = this.name,
                description = this.description,
                price = this.price,
                currency = this.currency,
                validFrom = this.validFrom,
                validTo = this.validTo,
                )
    }
}