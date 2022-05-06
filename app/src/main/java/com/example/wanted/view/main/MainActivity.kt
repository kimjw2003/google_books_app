package com.example.wanted.view.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanted.R
import com.example.wanted.databinding.ActivityMainBinding
import com.example.wanted.view.main.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("LogNotTimber")
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(0, viewModel)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        initLiveData()
        initListener()

        setBookRecyclerView()
        setRecyclerViewScrollListener()
    }

    private fun initLiveData() {
        viewModel.bookList.observe(this) {
            with(binding) {
                adapter.submitList(it.items?.toList())
                searchedItemNum.text = "검색된 도서 수 : ${it.totalItems}"
            }
        }

        viewModel.showProgress.observe(this) {
            with(binding) {
                if (it) {
                    loadingProgress.visibility = View.VISIBLE
                } else {
                    loadingProgress.visibility = View.GONE
                }
            }
        }
    }

    private fun initListener() {
        with(binding) {
            searchBtn.setOnClickListener {
                viewModel.getSearchedBookList(searchEt.text.toString())
            }
        }
    }

    private fun setBookRecyclerView() {
        adapter = MainAdapter()
        binding.bookRecyclerview.adapter = adapter
    }

    private fun setRecyclerViewScrollListener() {
        binding.apply {
            bookRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (recyclerView.canScrollVertically(-1)) {
                        viewModel.getSearchedBookList(searchEt.text.toString())
                    }
                }
            })
        }
    }

}