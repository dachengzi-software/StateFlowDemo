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

    private val _stateFlow = MutableStateFlow("安安安安卓")

    val stateFlow: StateFlow<String> = _stateFlow

    fun changeData(data: String) {
        _stateFlow.value = data
    }

}