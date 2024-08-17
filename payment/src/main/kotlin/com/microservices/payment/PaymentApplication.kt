package com.microservices.payment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = ["com.microservices.servicescommons"])
class PaymentApplication

fun main(args: Array<String>) {
    runApplication<PaymentApplication>(*args)
}
