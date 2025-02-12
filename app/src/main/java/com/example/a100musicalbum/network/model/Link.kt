package com.example.a100musicalbum.network.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable

data class Link(
    val self: String
)