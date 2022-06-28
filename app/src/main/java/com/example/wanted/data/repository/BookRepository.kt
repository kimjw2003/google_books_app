package com.example.wanted.data.repository

import com.example.wanted.data.ApiResponse
import com.example.wanted.data.ResponseBody
import com.example.wanted.data.domain.BookInfo
import com.example.wanted.data.domain.Books
import com.example.wanted.data.remote.BookApi
import com.example.wanted.util.Constants
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookApi: BookApi
) {

    suspend fun getBooks(title: String, startIndex: Int): ResponseBody<Books> {
        return try {
            ApiResponse.create(bookApi.getBooks(title, startIndex, Constants.MAX_RESULT))
        } catch (e: Exception) {
            ApiResponse.create(e)
        }
    }

}