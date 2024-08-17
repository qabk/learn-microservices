package com.microservices.payment.repositories

import com.microservices.payment.entities.PaymentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : JpaRepository<PaymentEntity, Long>{

}