package com.example.wanted.view.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanted.R
import com.example.wanted.databinding.ActivityMainBinding
import com.example.wanted.view.detail.DetailFragment
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
                        (adapter as BookAdapter).submitList(it)
                    }
                    searchedItemNum.text =
                        resources.getString(R.string.searched_books_text, it.size)
                }
            }

            bookItemOnClick.observe(this@MainActivity) {

                val bundle = Bundle().apply {
                    putParcelable("bookInfo" ,it)
                }

                supportFragmentManager
                    .beginTransaction()
                    .add(android.R.id.content, DetailFragment().apply { arguments = bundle })
                    .addToBackStack(null)
                    .commit()
            }

            showProgress.observe(this@MainActivity) {
                binding.loadingProgress.isVisible = it
            }
        }
    }

    private fun initListener() {
        with(binding) {

            searchBtn.setOnClickListener {
                viewModel.getSearchedBookList(binding.searchEt.text.toString())
            }

            searchEt.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    searchBtn.isEnabled = searchEt.text.isNotEmpty()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })
        }
    }

    private fun setBookRecyclerView() {
        binding.bookRecyclerview.adapter = BookAdapter()
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