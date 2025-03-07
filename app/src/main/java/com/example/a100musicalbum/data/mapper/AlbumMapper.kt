package com.example.a100musicalbum.data.mapper

import com.example.a100musicalbum.data.local.AlbumEntity
import com.example.a100musicalbum.data.network.dto.AlbumDto

fun AlbumDto.toAlbumEntity(): AlbumEntity {
    return AlbumEntity(
        id = this.id,
        name = this.name,
        artistName = this.artistName,
        artworkUrl = this.artworkUrl100,
        releaseDate = this.releaseDate
    )
}