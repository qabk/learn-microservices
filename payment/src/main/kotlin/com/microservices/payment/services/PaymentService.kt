package com.microservices.payment.services

import com.microservices.payment.entities.PaymentEntity
import com.microservices.payment.repositories.PaymentRepository
import com.microservices.servicescommons.clients.OrderClient
import com.microservices.servicescommons.clients.ProductClient
import com.microservices.servicescommons.constants.PaymentStatus
import com.microservices.servicescommons.dtos.PaymentDto
import com.microservices.servicescommons.requests.PaymentRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class PaymentService(
    private val orderClient: OrderClient,
    private val productClient: ProductClient,
    private val paymentRepository: PaymentRepository,
) {
    fun getPaymentById(id: Long): PaymentDto {
        val payment = paymentRepository.findById(id)
        if (payment.isPresent) return payment.get().toDto()
        else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, null);
        }
    }

    fun createPayment(orderId: String) {
        val order = orderClient.getOrderById(orderId)
        val items = order.products?.map { it.key }
        val products = items?.let { productClient.getProductByIds(it) }
        var price: Long = 0
        products!!.map {
            price += (order.products!![it.id]!!) * it.price!!
        }

        paymentRepository.save(
            PaymentEntity(
                0L,
                price,
                "$",
                PaymentStatus.NOT_PAID,
                orderId
            )
        )
    }

    fun processPayment(id: Long){
        val paymentOpt = paymentRepository.findById(id)
        if(paymentOpt.isPresent){
            paymentRepository.save(paymentOpt.get().copy(status = PaymentStatus.PENDING))
        }
    }

    fun updatePayment(id: Long, request: PaymentRequest) {
        val payment = paymentRepository.findById(id)
        if (payment.isPresent) {
            paymentRepository.save(payment.get().fromRequest(request))
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, null);
        }
    }

    fun deletePayment(id: Long): PaymentDto {
        val paymentOptional = paymentRepository.findById(id)
        if (paymentOptional.isPresent) {
            paymentRepository.deleteById(id)
            return paymentOptional.get().toDto()
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, null);
        }
    }

    private fun PaymentEntity.toDto() = PaymentDto(
        this.id,
        this.amount,
        this.currency,
        this.status,
        this.orderId,
    )

    private fun PaymentEntity.fromRequest(request: PaymentRequest) = this.copy(
        currency = request.currency ?: this.currency,
        status = request.status ?: this.status,
    )
}