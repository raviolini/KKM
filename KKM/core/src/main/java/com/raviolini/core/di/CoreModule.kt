package com.raviolini.core.di


import androidx.room.Room
import com.raviolini.core.BuildConfig
import com.raviolini.core.data.ItemRepos
import com.raviolini.core.data.source.local.LocalDataSource
import com.raviolini.core.data.source.local.room.KosDatabase
import com.raviolini.core.data.source.remote.RemoteDataSource
import com.raviolini.core.data.source.remote.network.ClientApi
import com.raviolini.core.domain.repository.IItemRepository
import net.sqlcipher.database.SQLiteDatabase.getBytes
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner

import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


private const val BASE_URL = BuildConfig.BASE_URL

val databaseModule = module{
    factory {
        get<KosDatabase>().kosDao()
    }
    single {
        val passPhrase : ByteArray = getBytes("KatalogKosMokletJos".toCharArray())
        val factory = SupportFactory(passPhrase)
        Room.databaseBuilder(
            androidContext(),
            KosDatabase::class.java, "Kos.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "kkmboiscuy.my.id"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/gsI9ZG1F6xtbg/BCCBQGrzaWgNHgLig7TFt3k7GVpQE=")
            .add(hostname, "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .add(hostname, "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor{ chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ClientApi::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IItemRepository>{
        ItemRepos(
            get(),
            get()
        )
    }
}