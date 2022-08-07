package com.example.google_books_app.data.repository

import com.example.google_books_app.data.ApiResponse
import com.example.google_books_app.data.ResponseBody
import com.example.google_books_app.data.domain.Books
import com.example.google_books_app.data.remote.BookApi
import com.example.google_books_app.util.Constants
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