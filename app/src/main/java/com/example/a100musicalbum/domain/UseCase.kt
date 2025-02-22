package com.example.a100musicalbum.domain

import com.example.a100musicalbum.data.repository.Repository
import com.example.a100musicalbum.data.model.Album

class UseCase(private val repository: Repository) {

    suspend operator fun invoke(): List<Album> {
        val feedWrapper = repository.fetchAlbums()
        return feedWrapper.feed?.albums ?: emptyList()
    }
}
