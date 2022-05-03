package com.example.wanted.di

import com.example.wanted.data.remote.BookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideBookApi(retrofit: Retrofit): BookApi = retrofit.create(BookApi::class.java)
}