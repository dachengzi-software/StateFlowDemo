package com.ananananzhuo.stateflowdemo.multiplestate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ananananzhuo.stateflowdemo.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
fun logEE(msg:String){
    Log.e("安安安安卓",msg)
}

/**
 * 同一个activity中注册两个StateFlow只有一个能回调
 */
class MultipleStateFlowActivity : AppCompatActivity() {
    val multipleViewModel by viewModels<MultipleViewModel> {
        ViewModelProvider.NewInstanceFactory()
    }
    var count =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_state_flow)
        val tv = findViewById<TextView>(R.id.tv_beupdate)
        lifecycleScope.launch {
            multipleViewModel.state11.collect {
                logEE(it)
                count++
                tv.text="收到StateFlow1数据更新 $count 次"
            }
            multipleViewModel.state22.collect {
                logEE(it)
                count++
                tv.text="收到StateFlow2数据更新 $count 次"
            }
        }
        findViewById<Button>(R.id.btn_state1_senddata).setOnClickListener {
            multipleViewModel.sendData1()
        }
        findViewById<Button>(R.id.btn_state2_senddata).setOnClickListener {
            multipleViewModel.sendData2()
        }
    }
}