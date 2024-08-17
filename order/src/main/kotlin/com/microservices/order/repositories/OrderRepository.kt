package com.microservices.order.repositories

import com.microservices.order.entities.OrderEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository  : MongoRepository<OrderEntity, String?>{

}