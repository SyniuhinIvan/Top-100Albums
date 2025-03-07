package com.example.a100musicalbum.domain

import com.example.a100musicalbum.data.repository.AlbumRepository
import com.example.a100musicalbum.ui.component.AlbumUI
import com.example.a100musicalbum.ui.component.toAlbumUI

class LoadAlbumsUseCase(private val albumRepository: AlbumRepository) {

    suspend operator fun invoke(): List<AlbumUI> {
        return albumRepository.fetchAlbums().map { it.toAlbumUI() }
    }
}
