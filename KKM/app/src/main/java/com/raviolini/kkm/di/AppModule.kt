package com.raviolini.kkm.di

import com.raviolini.core.domain.usecase.ItemInteractor
import com.raviolini.core.domain.usecase.ItemUseCase
import com.raviolini.kkm.detail.DetailVM
import com.raviolini.kkm.home.HomeVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ItemUseCase> {ItemInteractor(get())}
}

val viewModelModule = module {
    viewModel { HomeVM(get()) }
    viewModel { DetailVM(get()) }
}