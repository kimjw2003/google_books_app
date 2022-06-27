package com.example.wanted.view.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanted.R
import com.example.wanted.databinding.ActivityMainBinding
import com.example.wanted.view.main.adapter.BookAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("LogNotTimber")
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(binding) {
            setVariable(0, viewModel)
            vm = viewModel
            lifecycleOwner = this@MainActivity
        }

        initLiveData()
        initListener()

        setBookRecyclerView()
        setRecyclerViewScrollListener()
    }

    private fun initLiveData() {
        with(viewModel) {
            bookList.observe(this@MainActivity) {
                with(binding) {
                    bookRecyclerview.apply {
                        adapter = BookAdapter(viewModel)
                        (adapter as BookAdapter).submitList(it.items?.toList())
                    }
                    searchedItemNum.text =
                        resources.getString(R.string.searched_books_text, it.totalItems)
                }
            }

            showProgress.observe(this@MainActivity) {
                binding.loadingProgress.isVisible = it
            }
        }
    }

    private fun initListener() {
        binding.searchBtn.setOnClickListener {
            viewModel.getSearchedBookList(binding.searchEt.text.toString())
        }
    }

    private fun setBookRecyclerView() {
        binding.bookRecyclerview.adapter = BookAdapter(viewModel)
    }

    private fun setRecyclerViewScrollListener() {
        binding.apply {
            bookRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val lastVisibleItemPosition =
                        (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                    val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                    if (!recyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                        viewModel.getSearchedBookList(searchEt.text.toString())
                    }
                }
            })
        }
    }

}