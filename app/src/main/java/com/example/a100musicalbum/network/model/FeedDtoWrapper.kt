package com.example.a100musicalbum.network.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
// TODO: useless annotation
@InternalSerializationApi

@Serializable
data class FeedDtoWrapper(
    val feed: Feed? = null
)