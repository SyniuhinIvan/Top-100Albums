package com.example.a100musicalbum.domain

import com.example.a100musicalbum.data.repository.AlbumRepository
import com.example.a100musicalbum.data.network.dto.AlbumDto

class LoadAlbumsUseCase(private val albumRepository: AlbumRepository) : List<AlbumDto> {

    suspend operator fun invoke(): List<AlbumDto> {
        return albumRepository.fetchAlbums()
    }
}
