package com.example.a100musicalbum.di

import com.example.a100musicalbum.data.local.AlbumDb
import com.example.a100musicalbum.data.repository.Repository
import com.example.a100musicalbum.domain.UseCase
import com.example.a100musicalbum.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AlbumDb.getDatabase(androidContext()).albumDao() }
    single { UseCase(get()) }
    single { Repository.getInstance(get()) }
    viewModel { MainViewModel(get()) }
}
