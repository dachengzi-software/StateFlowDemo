package com.ananananzhuo.stateflowdemo.stateflow

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.ananananzhuo.stateflowdemo.R
import com.ananananzhuo.stateflowdemo.databinding.ActivityStateFlowBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StateFlowActivity : AppCompatActivity() {

    private val viewModel: StateFlowViewModel by viewModels()

    private lateinit var binding: ActivityStateFlowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_state_flow)

        binding.btnStateSendData.setOnClickListener {
            viewModel.changeData("公众号：安安安安卓")
        }

        lifecycleScope.launch {
            viewModel.stateFlow.collect {
                binding.tvDataShow.text = it
            }
        }

    }

}