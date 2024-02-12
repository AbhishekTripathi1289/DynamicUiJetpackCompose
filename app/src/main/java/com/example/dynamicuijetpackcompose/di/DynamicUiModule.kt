package com.example.starbuckapp.di

import com.example.dynamicuijetpackcompose.api.DynamicUiApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DynamicUiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit
    {
        return Retrofit.Builder().baseUrl("https://api.example.com").addConverterFactory(GsonConverterFactory.create()).build()
    }
    @Provides
    fun providesPostApi(retrofit: Retrofit):DynamicUiApi
    {
        return retrofit.create(DynamicUiApi::class.java)
    }
}