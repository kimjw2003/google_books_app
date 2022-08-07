package com.example.google_books_app.data.remote

import com.example.google_books_app.data.domain.Books
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    @GET("volumes")
    suspend fun getBooks(
        @Query("q") title: String,
        @Query("startIndex") startIndex: Int,
        @Query("maxResults") maxResults: Int
    ): Response<Books>
}