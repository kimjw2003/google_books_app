package com.example.wanted.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanted.data.domain.VolumeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(

) : ViewModel() {

    private val _volumeInfo = MutableLiveData<VolumeInfo?>()
    val volumeInfo: LiveData<VolumeInfo?>
        get() = _volumeInfo

    private val _previewLink = MutableLiveData<String?>()
    val previewLink: LiveData<String?>
        get() = _previewLink


    fun showPreviewPage(previewLink: String?) = viewModelScope.launch {
        _previewLink.postValue(previewLink)
    }

    fun showBookInfo(bookInfo: VolumeInfo?) = viewModelScope.launch {
        _volumeInfo.postValue(bookInfo)
    }

}