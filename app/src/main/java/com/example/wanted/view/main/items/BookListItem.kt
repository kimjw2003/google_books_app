package com.example.wanted.view.main.items

import com.example.wanted.data.domain.BookInfo

sealed class BookListItem {

    data class BookItem(
        val bookInfo: BookInfo,
        val onClick: () -> Unit
    ) : BookListItem()
}
