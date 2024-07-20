package com.microservices.product.controllers

import com.microservices.product.dtos.requests.ProductRequest
import com.microservices.product.services.ProductServices
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.Instant

@RestController
@RequestMapping("api/products/")
class ProductController(private val productService: ProductServices) {
    @PostMapping(
            value = ["/create"],
            produces = ["application/json", "*/*"],
    )
    fun createOrder(
            @RequestBody productRequest: ProductRequest
    ) {
        productService.createProduct(productRequest)
    }
}

//val name: String?,
//val description: String?,
//val price: BigDecimal?,
//val validFrom: Instant?,
//val validTo: Instant?,
//val currency: String?