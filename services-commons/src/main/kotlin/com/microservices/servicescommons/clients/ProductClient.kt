package com.microservices.servicescommons.clients

import com.microservices.servicescommons.dtos.ProductDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "product", url = "http://localhost:8084/api/products")
interface ProductClient {
    @GetMapping(
        produces = ["application/json", "*/*"],
        value = ["/id/{id}"],
    )
    fun getProductById(
        @PathVariable("id") id: String
    ): ProductDto

    @GetMapping(
        produces = ["application/json", "*/*"],
        value = ["/"],
    )
    fun getProductByIds(
        @RequestParam(name = "ids") ids: List<String>
    ): List<ProductDto>
}