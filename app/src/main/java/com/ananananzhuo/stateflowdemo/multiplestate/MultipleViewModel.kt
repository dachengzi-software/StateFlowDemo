package com.ananananzhuo.stateflowdemo.multiplestate

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * author  :mayong
 * function:
 * date    :2021/7/3
 **/
class MultipleViewModel : ViewModel() {
    private val state1 = MutableStateFlow<String>("哈哈哈1")
    private val state2 = MutableStateFlow<String>("哈哈哈哈2")
    private var count = 0
    val state11 = state1.asStateFlow()
    val state22 = state2.asStateFlow()


    fun sendData1() {
        count++
        state1.value = "state1被更改 $count"
    }

    fun sendData2() {
        count++
        state2.value = "state2被更改 $count"
    }
}