package com.wolt.restauranttime.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wolt.restauranttime.data.WoltService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [NetworkModule::class])
class WoltServiceModule {

    @Provides
    @WoltApplicationScope
    fun provideWoltService(retrofit: Retrofit): WoltService {
        return retrofit.create(WoltService::class.java)
    }

    @Provides
    @WoltApplicationScope
    fun provideGson(): Gson {
        return GsonBuilder().create();
    }

    @Provides
    @WoltApplicationScope
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://api.github.com/")
            .build()
    }

}
