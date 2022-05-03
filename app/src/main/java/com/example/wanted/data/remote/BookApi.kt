package com.example.wanted.data.remote

import com.example.wanted.data.domain.BookInfo
import com.example.wanted.data.domain.Books
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookApi {

    @GET("?q=a")
    suspend fun getAllBooks(): Response<Books>

    @GET("?q={title}")
    suspend fun getBookInfo(@Path("title") title: String): Response<BookInfo>
}