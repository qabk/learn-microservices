package com.microservices.order.controllers

import com.microservices.order.services.OderService
import com.microservices.servicescommons.dtos.OrderDto
import com.microservices.servicescommons.requests.OrderRequest
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Component
@RestController
@RequestMapping("api/orders/")
class OrderController(
    private val orderService: OderService
) {
    @PostMapping(
        value = ["/create"],
        produces = ["application/json", "*/*"],
    )
    fun createOrder(
        @RequestBody orderRequest: OrderRequest
    ) {
        orderService.createOrder(orderRequest)
    }

    @GetMapping(
        value = ["/id/{id}"],
        produces = ["application/json", "*/*"],
    )

    fun getOrderById(
        @PathVariable("id") id: String
    ): OrderDto {
        return orderService.getOrderById(id)
    }

    @GetMapping(
        value = ["/"],
        produces = ["application/json", "*/*"],
    )
    fun getOrderByIds(
        @RequestParam("ids") ids: List<String>
    ): List<OrderDto> {
        return orderService.getOrderByIds(ids)
    }

    @PatchMapping(
        value = ["/id/{id}"],
        produces = ["application/json", "*/*"],
    )
    fun updateOrder(
        @RequestBody orderRequest: OrderRequest,
        @PathVariable("id") id: String

    ) {
        orderService.updateOrder(id, orderRequest)
    }

    @DeleteMapping(
        value = ["/{id}"],
        produces = ["application/json", "*/*"],
    )
    fun deleteOrder(
        @PathVariable("id") id: String
    ) {
        orderService.deleteOrderById(id)
    }


}