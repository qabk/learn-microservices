package com.microservices.customer.services

import com.microservices.customer.entities.CustomerEntity
import com.microservices.customer.repositories.CustomerRepository
import com.microservices.servicescommons.dtos.CustomerDto
import com.microservices.servicescommons.requests.CustomerRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class CustomerServices(private val customerRepository: CustomerRepository) {
    fun createCustomer(customerRequest: CustomerRequest) {
        customerRepository.save(customerRequest.toEntity())
    }

    fun getCustomerById(id: String): CustomerDto {
        val customerEntity = customerRepository.findById(id)
        if (customerEntity.isPresent) return customerEntity.get().toResponse()
        else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, null);
        }
    }

    fun getCustomerByIds(ids: List<String>): List<CustomerDto> {
        return customerRepository.findAllById(ids).map { it.toResponse() }
    }

    fun updateCustomer(id: String, customerRequest: CustomerRequest) {
        val customer = customerRepository.findById(id)
        if (customer.isPresent) {
            customerRepository.save(
                customer.get().fromRequest(customerRequest)
            )
        }
    }

    fun deleteCustomer(id: String) {
        customerRepository.deleteById(id)
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

    private fun CustomerEntity.fromRequest(request: CustomerRequest): CustomerEntity {
        return CustomerEntity(
            id = id,
            firstName = request.firstName ?: this.firstName,
            lastName = request.lastName ?: this.lastName,
            dateOfBirth = request.dateOfBirth ?: this.dateOfBirth,
            address = request.address ?: address,
            email = request.email ?: this.email
        )
    }
}