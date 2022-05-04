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

    suspend fun getBookInfo(title: String, startIndex: Int): ResponseBody<Books>? {
        return try {
            ApiResponse.create(bookApi.getBookInfo(title, startIndex, 40))
        } catch (e: Exception) {
            ApiResponse.create(e)
        }
    }
}