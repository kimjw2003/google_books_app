package com.example.wanted.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.wanted.R
import com.example.wanted.data.domain.BookInfo
import com.example.wanted.databinding.ItemBooksBinding

class BookAdapter: ListAdapter<BookInfo, BookViewHolder>( DiffCallback() ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_books, parent, false)

        return BookViewHolder(ItemBooksBinding.bind(itemView))
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindItems(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<BookInfo>() {

        override fun areItemsTheSame(oldItem: BookInfo, newItem: BookInfo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BookInfo, newItem: BookInfo): Boolean {
            return oldItem == newItem
        }
    }
}