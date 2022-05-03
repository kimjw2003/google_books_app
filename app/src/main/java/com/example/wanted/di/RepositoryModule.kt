package com.example.wanted.di

import com.example.wanted.data.remote.BookApi
import com.example.wanted.data.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideUserRepository(bookApi: BookApi) = BookRepository(bookApi)
}