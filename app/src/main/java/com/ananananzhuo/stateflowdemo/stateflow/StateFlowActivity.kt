package com.ananananzhuo.stateflowdemo.stateflow

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ananananzhuo.stateflowdemo.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StateFlowActivity : AppCompatActivity() {

    private val btnSendData: Button by lazy { findViewById<Button>(R.id.btn_statesenddata) }
    private val tvShowData: TextView by lazy { findViewById<TextView>(R.id.tv_datashow) }

    private val viewModel: StateFlowViewModel by viewModels {
        ViewModelProvider.NewInstanceFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_flow)

        btnSendData.setOnClickListener {
            viewModel.changeData("公众号：安安安安卓")
        }
        lifecycleScope.launch {
            viewModel.stateFlow.collect {
                tvShowData.text = it
            }
        }

    }
}