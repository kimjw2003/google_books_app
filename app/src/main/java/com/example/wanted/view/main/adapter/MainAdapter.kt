package com.example.wanted.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wanted.R
import com.example.wanted.data.domain.BookInfo
import com.example.wanted.databinding.ItemBooksBinding
import com.example.wanted.view.main.MainViewModel

class MainAdapter(private val viewModel: MainViewModel
) : ListAdapter<BookInfo, MainViewHolder>(
    DiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_books, parent, false)

        return MainViewHolder(ItemBooksBinding.bind(itemView))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindItems(viewModel, getItem(position))
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