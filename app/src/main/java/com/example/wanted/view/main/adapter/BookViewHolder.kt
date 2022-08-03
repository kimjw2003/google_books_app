package com.example.wanted.view.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wanted.R
import com.example.wanted.data.domain.BookInfo
import com.example.wanted.databinding.ItemBooksBinding
import com.example.wanted.view.main.MainViewModel
import com.example.wanted.view.main.items.BookListItem

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