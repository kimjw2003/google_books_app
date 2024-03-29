package com.example.google_books_app.di

import com.example.google_books_app.data.remote.BookApi
import com.example.google_books_app.data.repository.BookRepository
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