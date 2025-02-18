package com.example.a100musicalbum.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO: useless annotation

@Serializable

data class Feed(
    val author: Author,
    val copyright: String,
    val country: String,
    val icon: String,
    val id: String,
    val links: List<Link>,
    @SerialName("results") val albums: List<Album>,
    val title: String,
    val updated: String
)