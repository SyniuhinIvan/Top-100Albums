package com.example.a100musicalbum.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


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