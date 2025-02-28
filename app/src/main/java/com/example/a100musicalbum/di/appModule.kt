package com.example.a100musicalbum.di

import com.example.a100musicalbum.data.local.AppDatabase
import com.example.a100musicalbum.data.repository.Repository
import com.example.a100musicalbum.domain.UseCase
import com.example.a100musicalbum.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Предоставляем экземпляр AlbumDao из базы данных
    single { AppDatabase.getDatabase(androidContext()).albumDao() }
    single { UseCase(get()) }
    // Предоставляем репозиторий как синглтон, используя метод getInstance()
    single { Repository.getInstance(get()) }
    viewModel { MainViewModel(get()) }
}
