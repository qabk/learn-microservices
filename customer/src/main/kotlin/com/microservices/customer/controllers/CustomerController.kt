package com.microservices.customer.controllers

import com.microservices.customer.services.CustomerServices
import com.microservices.servicescommons.dtos.CustomerDto
import com.microservices.servicescommons.requests.CustomerRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/customers/")
class CustomerController(
    private val customerServices: CustomerServices
) {

    @PostMapping(
        value = ["/create"],
        produces = ["application/json", "*/*"],
    )
    fun createCustomer(
        @RequestBody customerRequest: CustomerRequest
    ) {
        customerServices.createCustomer(customerRequest)
    }

    @GetMapping(
        value = ["/id/{id}"],
        produces = ["application/json", "*/*"],
        )

    fun getCustomerById(
        @PathVariable("id") id: String
    ): CustomerDto{
        return customerServices.getCustomerById(id)
    }

    @GetMapping(
        value = ["/"],
        produces = ["application/json", "*/*"],
    )
    fun getCustomerByListIds(
        @RequestParam("ids") ids: List<String>
    ) :List<CustomerDto>{
        return  customerServices.getCustomerByIds(ids)
    }

    @PatchMapping(
        value = ["/id/{id}"],
        produces = ["application/json", "*/*"],
    )
    fun updateCustomer(
        @PathVariable("id") id: String,
        @RequestBody customerRequest: CustomerRequest
    ) {
        customerServices.updateCustomer(id,customerRequest)
    }

    @DeleteMapping(
        value = ["/id/{id}"],
        produces = ["application/json", "*/*"],
    )

    fun deleteCustomerById(
        @PathVariable("id") id: String,
    ) {
        customerServices.deleteCustomer(id)
    }
}