package com.example.google_books_app.view.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.google_books_app.data.domain.BookInfo
import com.example.google_books_app.data.repository.BookRepository
import com.example.google_books_app.data.type.ResponseType
import com.example.google_books_app.view.main.items.BookListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _bookList = MutableLiveData<List<BookListItem>>()
    val bookList: LiveData<List<BookListItem>>
        get() = _bookList

    private val _bookInfo = MutableLiveData<BookInfo>()
    val bookInfo: LiveData<BookInfo>
        get() = _bookInfo

    private val _bookItemOnClick = MutableLiveData<BookInfo>()
    val bookItemOnClick: LiveData<BookInfo>
        get() = _bookItemOnClick

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    private var lastSearchedKeyWord: String = ""
    private val basicKeyWord = "a"
    private lateinit var list: MutableList<BookListItem>

    private var index = 0

    init {
        getSearchedBookList(basicKeyWord)
    }

    @SuppressLint("LogNotTimber")
    fun getSearchedBookList(bookTitle: String? = null) = viewModelScope.launch {

            val bookKeyWord = if (bookTitle.isNullOrBlank()) basicKeyWord else bookTitle

            _showProgress.postValue(true)

            val booksResponse = bookRepository.getBooks(bookKeyWord, index)

            if (booksResponse.result == ResponseType.SUCCESS) {

                booksResponse.body?.let { books ->

                    val listItem = mutableListOf<BookListItem>()

                    books.items?.map {
                        BookListItem.BookItem(
                            bookInfo = it,
                            onClick = { _bookItemOnClick.postValue(it) }
                        )
                    }?.forEachIndexed { index, bookItem ->
                        listItem.add(bookItem)
                    }

                    list = _bookList.value?.toMutableList() ?: mutableListOf()

                    if (lastSearchedKeyWord == bookKeyWord) {
                        listItem.let(list::addAll)
                        _bookList.postValue(list)
                    } else {
                        _bookList.postValue(listItem)
                    }

                    lastSearchedKeyWord = bookKeyWord
                }
                index += 40
                _showProgress.postValue(false)

            } else Log.d("test:", "Not Connected : ${booksResponse.error?.message}")
        }

}