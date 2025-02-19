package com.example.a100musicalbum.network.model

import kotlinx.serialization.Serializable

@Serializable
data class FeedDtoWrapper(
    val feed: Feed? = null
)