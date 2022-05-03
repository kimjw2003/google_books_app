package com.example.wanted.data.repository

import com.example.wanted.data.ApiResponse
import com.example.wanted.data.ResponseBody
import com.example.wanted.data.domain.BookInfo
import com.example.wanted.data.domain.Books
import com.example.wanted.data.remote.BookApi
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookApi: BookApi
) {

    suspend fun getBooks(): ResponseBody<Books>? {
        return try {
            ApiResponse.create(bookApi.getAllBooks())
        } catch (e: Exception) {
            ApiResponse.create(e)
        }
    }

    suspend fun getBookInfo(title: String): ResponseBody<Books>? {
        return try {
            ApiResponse.create(bookApi.getBookInfo(title))
        } catch (e: Exception) {
            ApiResponse.create(e)
        }
    }
}