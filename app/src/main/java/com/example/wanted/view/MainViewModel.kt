package com.example.wanted.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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


    fun getAllBooks() {
        viewModelScope.launch {
            val booksResponse = bookRepository.getBooks()

            booksResponse?.body?.let {
                _allBooks.postValue(it)
            }
        }
    }


}