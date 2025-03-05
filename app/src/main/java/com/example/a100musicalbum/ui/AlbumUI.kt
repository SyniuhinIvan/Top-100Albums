package com.example.a100musicalbum.ui

import android.os.Parcelable
import com.example.a100musicalbum.data.network.dto.AlbumDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumUI(
    val title: String,
    val subtitle: String,
    val image: String,
    val id: String
) : Parcelable

fun AlbumDto.toAlbumUI() = AlbumUI(
    title = this.name,
    subtitle = this.artistName,
    image = this.artworkUrl100,
    id = this.id
)