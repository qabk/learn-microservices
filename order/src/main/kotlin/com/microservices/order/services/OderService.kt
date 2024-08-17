package com.microservices.order.services

import com.microservices.order.entities.OrderEntity
import com.microservices.order.repositories.OrderRepository
import com.microservices.servicescommons.clients.ProductClient
import com.microservices.servicescommons.dtos.OrderDto
import com.microservices.servicescommons.requests.OrderRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Instant

@Service
class OderService(
    private val orderRepository: OrderRepository,
    private val productClient: ProductClient,
) {
    fun createOrder(orderRequest: OrderRequest) {
        orderRepository.save(
            orderRequest.toEntity()
        )
    }

    fun deleteOrderById(id: String) {
        orderRepository.deleteById(id)
    }

    fun getOrderById(id: String): OrderDto {
        val order = orderRepository.findById(id)
        if (order.isPresent) return order.get().toDto()
        else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, null);
        }
    }

    fun getOrderByIds(ids: List<String>): List<OrderDto> {
        return orderRepository.findAllById(ids).map { it.toDto() }
    }

    fun updateOrder(id: String, orderRequest: OrderRequest) {
        val updatedOrder = orderRepository.findById(id)
        if (updatedOrder.isPresent) {
            orderRepository.save(updatedOrder.get().fromRequest(orderRequest))
        }
    }


    fun OrderEntity.toDto(): OrderDto {
        return OrderDto(
            id = this.id,
            products = this.products,
            customerDto = this.orderCustomer,
            orderTime = this.orderTime.toString(),
            isExecuted = this.isExecuted ?: true,
        )
    }

    fun OrderEntity.fromRequest(request: OrderRequest): OrderEntity {
        return this.copy(
            orderCustomer = request.customerId ?: this.orderCustomer,
            products = request.products ?: this.products,
            orderTime = Instant.now()
        )
    }

    fun OrderRequest.toEntity(): OrderEntity {
        return OrderEntity(
            orderCustomer = this.customerId,
            products = this.products,
            orderTime = Instant.now(),
            isExecuted = this.isExecuted,
        )
    }
}