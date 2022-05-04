package com.example.wanted.view.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.wanted.R
import com.example.wanted.data.domain.BookInfo
import com.example.wanted.databinding.ActivityMainBinding
import com.example.wanted.view.main.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private val bookList = arrayListOf<BookInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(0, viewModel)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        viewModel.getAllBooks()

        initLiveData()
        initListener()
    }

    @SuppressLint("LogNotTimber")
    private fun initLiveData() {
        viewModel.allBooks.observe(this) {
            binding.apply {
                bookList.clear()
                bookList.addAll(it.items!!)

                setBookRecyclerView()
                searchedItemNum.text = "검색된 도서 수 : ${it.totalItems}"
            }
        }

        viewModel.searchBookList.observe(this) {
            binding.apply {
                bookList.clear()
                bookList.addAll(it.items!!)

                setBookRecyclerView()
                searchedItemNum.text = "검색된 도서 수 : ${it.totalItems}"
            }
        }

    }

    private fun initListener() {
        binding.apply {
            searchBtn.setOnClickListener {
                viewModel.getSearchedBookList(searchEt.text.toString())
            }
        }
    }


    private fun setBookRecyclerView() {
        binding.apply {
            bookRecyclerview.apply {
                adapter = MainAdapter(viewModel)
                (adapter as MainAdapter).submitList(bookList)
                scheduleLayoutAnimation()
            }
        }
    }

}