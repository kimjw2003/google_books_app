package com.example.wanted.di

import com.example.wanted.data.remote.BookApi
import com.example.wanted.data.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(bookApi: BookApi) = BookRepository(bookApi)
}