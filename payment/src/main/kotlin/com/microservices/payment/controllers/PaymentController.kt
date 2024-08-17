package com.microservices.payment.controllers

import com.microservices.payment.services.PaymentService
import com.microservices.servicescommons.requests.PaymentRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/payments/")
class PaymentController(
    private val paymentService: PaymentService
) {
    @GetMapping(
        value = ["/id/{id}"],
        produces = ["application/json", "*/*"],
    )
    fun getPayment(@PathVariable("id") id: Long) = paymentService.getPaymentById(id)

    @PostMapping(
        produces = ["application/json", "*/*"],
        value = ["/create/orderId/{orderId}"]
    )
    fun createPayment(@PathVariable("orderId") orderId: String) = paymentService.createPayment(orderId)


    @PatchMapping(
        produces = ["application/json", "*/*"], value = ["update/id/{id}"]
    )

    fun updatePayment(
        @RequestBody paymentRequest: PaymentRequest, @PathVariable("id") id: Long
    ) {
        paymentService.updatePayment(id, paymentRequest)
    }

    @DeleteMapping(
        produces = ["application/json", "*/*"], value = ["delete/id/{id}"]
    )
    fun deletePayment(@PathVariable("id") id: Long) = paymentService.deletePayment(id)
}