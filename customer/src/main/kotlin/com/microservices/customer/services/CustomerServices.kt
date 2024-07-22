package com.microservices.customer.services

import com.microservices.customer.entities.CustomerEntity
import com.microservices.customer.repositories.CustomerRepository
import com.microservices.servicescommons.dtos.CustomerDto
import com.microservices.servicescommons.requests.CustomerRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerServices(private val customerRepository: CustomerRepository) {
    fun createCustomer(customerRequest: CustomerRequest) {
        customerRepository.save(customerRequest.toEntity())
    }

    fun getCustomerById(id: String): Optional<CustomerDto> {
        return customerRepository.findById(id).map { it.toResponse() }
    }

    fun getCustomerByIds(ids: List<String>): List<CustomerDto> {
        return customerRepository.findAllById(ids).map { it.toResponse() }
    }

    fun updateCustomer(id: String, customerRequest: CustomerRequest) {
        val customer = customerRepository.findById(id)
        if (customer.isPresent) {
            customerRepository.save(customer.get().copy(
                    id,
                    customerRequest.firstName,
                    customerRequest.lastName,
                    customerRequest.dateOfBirth,
                    customerRequest.address,
                    customerRequest.email
            ))
        }
    }

    private fun CustomerEntity.toResponse(): CustomerDto {
        return CustomerDto(
                this.id,
                this.firstName,
                this.lastName,
                this.dateOfBirth,
                this.address,
                this.email,
        )
    }

    private fun CustomerRequest.toEntity(): CustomerEntity {
        return CustomerEntity(
                null,
                this.firstName,
                this.lastName,
                this.dateOfBirth,
                this.address,
                this.email,
        )
    }
}