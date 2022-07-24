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

    private val _state1 = MutableStateFlow<String>("哈哈哈1")

    val state1 = _state1.asStateFlow()

    private val _state2 = MutableStateFlow<String>("哈哈哈哈2")

    val state2 = _state2.asStateFlow()

    private var count = 0

    fun sendData1() {
        count++
        _state1.value = "state1被更改 $count"
    }

    fun sendData2() {
        count++
        _state2.value = "state2被更改 $count"
    }

}