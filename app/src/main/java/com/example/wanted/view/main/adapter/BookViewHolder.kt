package com.example.wanted.view.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wanted.R
import com.example.wanted.data.domain.BookInfo
import com.example.wanted.databinding.ItemBooksBinding
import com.example.wanted.view.main.MainViewModel

class BookViewHolder(
    private val binding: ItemBooksBinding,
    private val viewModel: MainViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItems(
        bookInfo: BookInfo
    ) {

        binding.vm = viewModel
        binding.bookInfo = bookInfo
        binding.executePendingBindings()
    }
}