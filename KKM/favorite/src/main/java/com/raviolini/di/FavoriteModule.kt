package com.raviolini.di

import com.raviolini.favorite.FavoriteVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module{
    viewModel { FavoriteVM(get()) }
}