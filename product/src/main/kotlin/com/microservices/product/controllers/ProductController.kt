package com.microservices.product.controllers

import com.microservices.product.services.ProductServices
import com.microservices.servicescommons.dtos.ProductDto
import com.microservices.servicescommons.requests.ProductRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("api/products/")
class ProductController(private val productService: ProductServices) {
    @PostMapping(
            value = ["/create"],
            produces = ["application/json", "*/*"],
    )
    fun createProduct(
            @RequestBody productRequest: ProductRequest
    ) {
        productService.createProduct(productRequest)
    }

    @DeleteMapping(
            produces = ["application/json", "*/*"],
            value = ["/delete/{id}"],
    )
    fun deleteProductById(
            @PathVariable("id") id: String
    ) {
        productService.deleteProductById(id)
    }

    @GetMapping(
            produces = ["application/json", "*/*"],
            value = ["/{id}"],
    )

    fun getProductById(
            @PathVariable("id") id: String
    ): ProductDto {
        val response = productService.findProductById(id)
        if (response.isPresent) return response.get()
        else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, null);
        }
    }

    @GetMapping(
            produces = ["application/json", "*/*"],
            value = [""],
    )
    fun getProductByIds(
            @RequestParam(name = "ids") ids: List<String>
    ): List<ProductDto> {
        productService.findAllProductByIds(ids).let {
            if (it.isNotEmpty()) return it
            else {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, null);
            }
        }
    }

    @PatchMapping(
            produces = ["application/json", "*/*"],
            value = ["/{id}"],
    )
    fun patchProduct(
            @PathVariable("id") id: String,
            @RequestBody productRequest: ProductRequest
    ) {
        productService.updateProduct(id,productRequest)
    }

}

//val name: String?,
//val description: String?,
//val price: BigDecimal?,
//val validFrom: Instant?,
//val validTo: Instant?,
//val currency: String?