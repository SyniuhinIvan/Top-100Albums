package com.example.a100musicalbum.network.model

import kotlinx.serialization.Serializable

// TODO: useless annotation

@Serializable
data class FeedDtoWrapper(
    val feed: Feed? = null
)