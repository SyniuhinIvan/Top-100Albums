package com.example.a100musicalbum.data.network.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class AlbumDto(
    val artistId: String,
    val artistName: String,
    val artistUrl: String,
    val artworkUrl100: String,
    val genres: List<Genre>,
    val id: String,
    val kind: String,
    val name: String,
    val releaseDate: String,
    val url: String
) : Parcelable

@Serializable
data class Author(
    val name: String,
    val url: String
)

@Serializable
data class Feed(
    val author: Author,
    val copyright: String,
    val country: String,
    val icon: String,
    val id: String,
    val links: List<Link>,
    @SerialName("results") val albums: List<AlbumDto>,
    val title: String,
    val updated: String
)

@Serializable
data class FeedDtoWrapper(
    val feed: Feed? = null
)

@Parcelize
@Serializable
data class Genre(
    val name: String
) : Parcelable

@Serializable
data class Link(
    val self: String
)