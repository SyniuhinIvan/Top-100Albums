package com.example.a100musicalbum.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FeedDtoWrapper(
    val feed: Feed? = null
)