package com.wolt.restauranttime.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class NetworkModule {

    @Provides
    @WoltApplicationScope
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @WoltApplicationScope
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor {
                val newRequest = it.request().newBuilder()
                    .addHeader(
                        "secret-key",
                        "\$2a\$10\$fyUTtVOJkL4BOhAJEm0tY.AZZO/0WVLs429tgXnaE3SnZ2H/I2Ruq"
                    )
                    .build()
                it.proceed(newRequest)
            }
            .build()
    }
}