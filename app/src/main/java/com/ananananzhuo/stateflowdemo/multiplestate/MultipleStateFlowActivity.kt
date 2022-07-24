package com.ananananzhuo.stateflowdemo.multiplestate

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.ananananzhuo.stateflowdemo.R
import com.ananananzhuo.stateflowdemo.databinding.ActivityMultipleStateFlowBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun logEE(msg: String) {
    Log.e("安安安安卓", msg)
}

/**
 * 同一个activity中注册两个StateFlow只有一个能回调
 */
class MultipleStateFlowActivity : AppCompatActivity() {

    private val multipleViewModel: MultipleViewModel by viewModels()

    private lateinit var binding: ActivityMultipleStateFlowBinding

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_multiple_state_flow)

        lifecycleScope.launch {
            multipleViewModel.state11.collect {
                logEE(it)
                count++
                binding.tvBeupdate.text = "收到StateFlow1数据更新 $count 次"
            }
            multipleViewModel.state22.collect {
                logEE(it)
                count++
                binding.tvBeupdate.text = "收到StateFlow2数据更新 $count 次"
            }
        }

        binding.btnState1Senddata.setOnClickListener {
            multipleViewModel.sendData1()
        }

        binding.btnState2Senddata.setOnClickListener {
            multipleViewModel.sendData2()
        }

    }
}