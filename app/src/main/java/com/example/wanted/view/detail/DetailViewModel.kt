package com.example.wanted.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wanted.data.domain.BookInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(

) : ViewModel() {

    private val _bookInfo = MutableLiveData<BookInfo>()
    val bookInfo: LiveData<BookInfo>
        get() = _bookInfo


}