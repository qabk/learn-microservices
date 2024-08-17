package com.microservices.servicescommons.clients

import com.microservices.servicescommons.dtos.CustomerDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@FeignClient(value = "customer", url = "http://localhost:8081/api/customers")

interface CustomerClient {
    @GetMapping("/{id}")
    fun getCustomerById(
        @PathVariable("id") id: String
    ): CustomerDto
}