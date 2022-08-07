package com.example.google_books_app.view.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.google_books_app.databinding.ItemBooksBinding
import com.example.google_books_app.view.main.items.BookListItem

class BookViewHolder(
    private val binding: ItemBooksBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItems(
        bookItem: BookListItem.BookItem
    ) {

        binding.bookItem = bookItem
        binding.executePendingBindings()
    }
}