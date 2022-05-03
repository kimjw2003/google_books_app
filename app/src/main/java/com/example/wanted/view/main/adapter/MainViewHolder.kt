package com.example.wanted.view.main.adapter

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wanted.R
import com.example.wanted.data.domain.BookInfo
import com.example.wanted.data.domain.Books
import com.example.wanted.databinding.ItemBooksBinding
import com.example.wanted.view.main.MainViewModel

class MainViewHolder (private val binding: ItemBooksBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItems(
        viewModel: MainViewModel,
        bookInfo: BookInfo
    ) {

        binding.vm = viewModel
        binding.itemBookTitle.text = bookInfo.volumeInfo?.title

        Glide.with(itemView)
            .load(bookInfo.volumeInfo?.imageLinks?.thumbnail.toString())
            .error(R.drawable.default_img)
            .into(binding.itemBookImage)

        binding.itemBookPublisher.text = bookInfo.volumeInfo?.publisher ?: ""
        binding.executePendingBindings()
    }
}