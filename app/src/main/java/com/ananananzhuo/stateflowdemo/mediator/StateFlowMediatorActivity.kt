package com.ananananzhuo.stateflowdemo.mediator

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.ananananzhuo.stateflowdemo.R
import com.ananananzhuo.stateflowdemo.databinding.ActivityStateFlowMediatorBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StateFlowMediatorActivity : AppCompatActivity() {

    private val viewModel: StateFlowMediatorViewModel by viewModels()

    private lateinit var binding: ActivityStateFlowMediatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_state_flow_mediator)

        binding.btnMediator1.setOnClickListener {
            viewModel.flow1pp()
        }

        binding.btnMediator2.setOnClickListener {
            viewModel.flow2pp()
        }

        lifecycleScope.launch {
            viewModel.flow.collect {
                binding.tvMediator.text = if (it > 10) "亲爱的安安安安卓同学您已经点击了$it 次了，再点也不和你玩了" else "您已经点击了$it 次"
            }
        }

    }
}
