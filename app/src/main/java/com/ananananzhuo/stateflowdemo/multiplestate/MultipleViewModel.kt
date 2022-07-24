package com.ananananzhuo.stateflowdemo.multiplestate

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * author  :mayong
 * function:
 * date    :2021/7/3
 **/
class MultipleViewModel : ViewModel() {

    private val _state11 = MutableStateFlow<String>("哈哈哈1")

    val state11 = _state11.asStateFlow()

    private val _state22 = MutableStateFlow<String>("哈哈哈哈2")

    val state22 = _state22.asStateFlow()

    private var count = 0

    fun sendData1() {
        count++
        _state11.value = "state1被更改 $count"
    }

    fun sendData2() {
        count++
        _state22.value = "state2被更改 $count"
    }

}