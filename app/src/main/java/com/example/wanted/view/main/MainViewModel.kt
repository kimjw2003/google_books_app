package com.example.wanted.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanted.data.domain.BookInfo
import com.example.wanted.data.domain.Books
import com.example.wanted.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val bookRepository: BookRepository
): ViewModel() {

    private val _allBooks = MutableLiveData<Books>()
    val allBooks: LiveData<Books>
        get() = _allBooks

    private val _searchBookList = MutableLiveData<Books>()
    val searchBookList: LiveData<Books>
        get() = _searchBookList

    private val _bookInfo = MutableLiveData<List<BookInfo>>()
    val bookInfo: LiveData<List<BookInfo>>
        get() = _bookInfo


    fun getAllBooks() {
        viewModelScope.launch {
            val booksResponse = bookRepository.getBooks()

            booksResponse?.body?.let {
                _allBooks.postValue(it)
            }
        }
    }

    fun getSearchedBookList(bookTitle: String) {
        viewModelScope.launch {
            val bookInfoResponse = bookRepository.getBookInfo(bookTitle)

            bookInfoResponse?.body?.let {
                _searchBookList.postValue(it)
            }
        }
    }


//    fun getBookInfo(bookTitle: String) {
//        viewModelScope.launch {
//            val bookInfoResponse = bookRepository.getBookInfo(bookTitle)
//
//            bookInfoResponse?.body?.let {
//                _bookInfo.postValue(it)
//            }
//        }
//    }

}