package com.example.chat.entities

import jakarta.persistence.Entity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "messages")
data class MessageEntity (
    @Id
    val id: String? = null,
    val senderId: String? = null,
    val receiverId: String? = null,

)