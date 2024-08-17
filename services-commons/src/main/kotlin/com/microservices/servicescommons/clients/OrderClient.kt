package com.microservices.servicescommons.clients

import com.microservices.servicescommons.dtos.OrderDto
import com.microservices.servicescommons.requests.OrderRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(value = "order", url = "http://localhost:8082/api/orders")

interface OrderClient {
    @GetMapping(
        value = ["/id/{id}"],
        produces = ["application/json", "*/*"],
    )

    fun getOrderById(
        @PathVariable("id") id: String
    ): OrderDto

    @PatchMapping(
        value = ["/{id}"],
        produces = ["application/json", "*/*"],
    )
    fun updateOrder(
        @RequestBody orderRequest: OrderRequest,
        @PathVariable("id") id: String
    )

}