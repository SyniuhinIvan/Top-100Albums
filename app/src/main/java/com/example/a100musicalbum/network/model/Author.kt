package com.example.a100musicalbum.network.model

import kotlinx.serialization.Serializable


@Serializable
data class Author(
    val name: String,
    val url: String
)