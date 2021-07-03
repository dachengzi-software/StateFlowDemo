package com.ananananzhuo.stateflowdemo.stateflow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * author  :mayong
 * function:
 * date    :2021/6/28
 **/
class StateFlowViewModel : ViewModel() {
    private val mutableStateFlow = MutableStateFlow("安安安安卓")
    val stateFlow: StateFlow<String> = mutableStateFlow

    fun changeData(data: String) {
        mutableStateFlow.value = data
    }
}