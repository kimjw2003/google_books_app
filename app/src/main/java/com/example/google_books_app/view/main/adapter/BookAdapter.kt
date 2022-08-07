package com.example.google_books_app.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.google_books_app.R
import com.example.google_books_app.databinding.ItemBooksBinding
import com.example.google_books_app.view.main.items.BookListItem

class BookAdapter : ListAdapter<BookListItem, BookViewHolder>( DiffCallback() ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_books, parent, false)

        return BookViewHolder(ItemBooksBinding.bind(itemView))
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindItems(getItem(position) as BookListItem.BookItem)
    }

    private class DiffCallback : DiffUtil.ItemCallback<BookListItem>() {

        override fun areItemsTheSame(oldItem: BookListItem, newItem: BookListItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BookListItem, newItem: BookListItem): Boolean {
            return oldItem == newItem
        }
    }
}