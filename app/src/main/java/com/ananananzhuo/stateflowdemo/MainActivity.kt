package com.ananananzhuo.stateflowdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ananananzhuo.stateflowdemo.mediator.StateFlowMediatorActivity
import com.ananananzhuo.stateflowdemo.multiplestate.MultipleStateFlowActivity
import com.ananananzhuo.stateflowdemo.stateflow.StateFlowActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_stateflow).setOnClickListener {
            startActivity(Intent(this, StateFlowActivity::class.java))
        }
        findViewById<Button>(R.id.btn_statemediator).setOnClickListener {
            startActivity(Intent(this, StateFlowMediatorActivity::class.java))
        }
        findViewById<Button>(R.id.btn_multiplestate).setOnClickListener {
            startActivity(Intent(this, MultipleStateFlowActivity::class.java))
        }
    }
}