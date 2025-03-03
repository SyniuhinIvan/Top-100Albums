package com.example.a100musicalbum.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
/* TODO: сам пакет model лучше положить ближе к месту использования */
/* TODO: разделить этот Album на AlbumDto для нетворка и на AlbumUI (например такое название) для UI/Presentation слоя */
/* для остальных моделей также */
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
