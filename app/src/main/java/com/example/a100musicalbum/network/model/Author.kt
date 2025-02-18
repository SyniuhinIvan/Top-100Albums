package com.example.a100musicalbum.network.model

import kotlinx.serialization.Serializable

// TODO: useless annotation

@Serializable
data class Author(
    val name: String,
    val url: String
)