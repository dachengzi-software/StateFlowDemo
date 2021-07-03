package com.ananananzhuo.stateflowdemo.mediator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

/**
 * author  :mayong
 * function:
 * date    :2021/6/28
 **/
class StateFlowMediatorViewModel : ViewModel() {
    private var count1 = 0//第一个按钮点击的次数
    private var count2 = 0//第二个按钮点击的次数
    private val flow1 = MutableStateFlow(0)
    private val flow2 = MutableStateFlow(0)
    val flow = flow1.combine(flow2) { data1, data2 ->
        data1 + data2//将两个flow融合，分别点击的数量相加
    }

    fun flow1pp() {
        flow1.value = ++count1//点击第一个按钮数量加1
    }

    fun flow2pp() {
        flow2.value = ++count2//点击第二个按钮数量加1
    }
}