package com.ananananzhuo.stateflowdemo.mediator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ananananzhuo.stateflowdemo.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StateFlowMediatorActivity : AppCompatActivity() {
    val model: StateFlowMediatorViewModel by viewModels<StateFlowMediatorViewModel> {
        ViewModelProvider.NewInstanceFactory()
    }
    var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_flow_mediator)
        textView = findViewById<TextView>(R.id.tv_mediator)
        findViewById<Button>(R.id.btn_mediator1).setOnClickListener {
            model.flow1pp()
        }
        findViewById<Button>(R.id.btn_mediator2).setOnClickListener {
            model.flow2pp()
        }

        lifecycleScope.launch {
            model.flow.collect {
                textView?.text = if (it > 10) "亲爱的安安安安卓同学您已经点击了$it 次了，再点也不和你玩了" else "您已经点击了$it 次"
            }
        }
    }
}
