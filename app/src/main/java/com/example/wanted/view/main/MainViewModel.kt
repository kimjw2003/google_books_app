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

    var lastSearchedKeyWord: String = ""

    init {
        getSearchedBookList("a", 0)
    }

    @SuppressLint("LogNotTimber")
    fun getSearchedBookList(bookTitle: String? = null, startIndex: Int) {
        viewModelScope.launch {

            val bookKeyWord = if (bookTitle.isNullOrBlank()) "a" else bookTitle // a 변수로 선언

            val bookInfoResponse = bookRepository.getBookInfo(bookKeyWord, startIndex)

            if(bookInfoResponse?.result == ResponseType.SUCCESS) {

                bookInfoResponse.body?.let { books ->
                    val list: MutableList<BookInfo> =
                        _bookList.value?.items?.toMutableList() ?: mutableListOf()
                    if (lastSearchedKeyWord == bookKeyWord) {
                        books.items?.let(list::addAll)
                        _bookList.postValue(books.copy(items = list))
                    } else {
                        _bookList.postValue(books)
                    }

                    lastSearchedKeyWord = bookKeyWord
                }
            } else Log.d("test:", "Not Connected : ${bookInfoResponse?.error?.message}") //throw 던지는것도 ㄱㅊ
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