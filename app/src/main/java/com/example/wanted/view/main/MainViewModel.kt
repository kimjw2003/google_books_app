package com.example.wanted.view.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanted.data.domain.BookInfo
import com.example.wanted.data.domain.Books
import com.example.wanted.data.repository.BookRepository
import com.example.wanted.data.type.ResponseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val bookRepository: BookRepository
): ViewModel() {

    private val _bookList = MutableLiveData<Books>()
    val bookList: LiveData<Books>
        get() = _bookList

    private val _bookInfo = MutableLiveData<List<BookInfo>>()
    val bookInfo: LiveData<List<BookInfo>>
        get() = _bookInfo

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    private var lastSearchedKeyWord: String = ""
    private val basicKeyWord = "a"

    private var index = 0

    init {
        getSearchedBookList(basicKeyWord)
    }

    @SuppressLint("LogNotTimber")
    fun getSearchedBookList(bookTitle: String? = null) {
        viewModelScope.launch {

            _showProgress.postValue(true)

            val bookKeyWord = if (bookTitle.isNullOrBlank()) basicKeyWord else bookTitle

            val bookInfoResponse = bookRepository.getBookInfo(bookKeyWord, index)

            if (bookInfoResponse?.result == ResponseType.SUCCESS) {

                bookInfoResponse.body?.let { books ->
                    val list: MutableList<BookInfo> =
                        _bookList.value?.items?.toMutableList() ?: mutableListOf()

                    if (lastSearchedKeyWord == bookKeyWord) {
                        books.items?.let(list::addAll)
                        _bookList.postValue(books.copy(items = list))
                    } else {
                        _bookList.postValue(books)
                    }

                    index += 40
                    lastSearchedKeyWord = bookKeyWord

                    _showProgress.postValue(false)
                }
            } else Log.d("test:", "Not Connected : ${bookInfoResponse?.error?.message}")
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