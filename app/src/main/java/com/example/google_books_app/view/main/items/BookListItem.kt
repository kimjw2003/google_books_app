package com.example.google_books_app.view.main.items

import com.example.google_books_app.data.domain.BookInfo

sealed class BookListItem {

    data class BookItem(
        val bookInfo: BookInfo,
        val onClick: () -> Unit
    ) : BookListItem()
}
