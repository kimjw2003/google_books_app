package com.example.google_books_app.data

import com.example.google_books_app.data.type.ResponseType
import com.google.gson.GsonBuilder
import retrofit2.Response

sealed class ApiResponse<T> {

    companion object {

        fun <T> create(exception: Exception): ResponseBody<T> {

            return ResponseBody(ResponseType.FAIL, exception.hashCode(), exception.message.toString(), null)
        }

        fun <T> create(response: Response<T>): ResponseBody<T> {

            val body = response.body()

            return if (response.isSuccessful) ResponseBody(ResponseType.SUCCESS, response.code(), response.message(), body)
            else {

                val errorResponse = GsonBuilder().create().fromJson(response.errorBody()?.string(), Error::class.java)

                return ResponseBody(ResponseType.FAIL, response.code(), response.body().toString(), body, errorResponse)
            }
        }
    }
}

data class ResponseBody<T>(val result: ResponseType, val code: Int, val message: String?, val body: T?, val error: Error? = null) : ApiResponse<T>()

data class Error(val code: Int, val message: String, val errors: List<Errors>)

data class Errors(val message: String, val domain: String, val reason: String)