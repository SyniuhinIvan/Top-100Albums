package com.example.a100musicalbum.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class Album(
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
