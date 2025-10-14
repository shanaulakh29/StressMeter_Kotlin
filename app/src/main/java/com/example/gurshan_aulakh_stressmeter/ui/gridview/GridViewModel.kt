package com.example.gurshan_aulakh_stressmeter.ui.gridview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GridViewModel: ViewModel() {
    var array = MutableLiveData<List<Int>>()
}
//    private val _text = MutableLiveData<String>().apply {
//        value = "This is gridview Fragment"
//    }
//    val text: LiveData<String> = _text