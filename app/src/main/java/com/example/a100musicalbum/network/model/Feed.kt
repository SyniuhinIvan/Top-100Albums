package com.example.a100musicalbum.network.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
// TODO: useless annotation
@InternalSerializationApi

@Serializable

data class Feed(
    val author: Author,
    val copyright: String,
    val country: String,
    val icon: String,
    val id: String,
    val links: List<Link>,
    val results: List<Result>,
    val title: String,
    val updated: String
)