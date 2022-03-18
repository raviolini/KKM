@file:Suppress("unused")

package com.raviolini.kkm

import android.app.Application
import com.raviolini.core.di.databaseModule
import com.raviolini.core.di.networkModule
import com.raviolini.core.di.repositoryModule
import com.raviolini.kkm.di.useCaseModule
import com.raviolini.kkm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}