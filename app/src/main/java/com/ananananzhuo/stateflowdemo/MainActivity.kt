package com.ananananzhuo.stateflowdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ananananzhuo.stateflowdemo.mediator.StateFlowMediatorActivity
import com.ananananzhuo.stateflowdemo.multiplestate.MultipleStateFlowActivity
import com.ananananzhuo.stateflowdemo.stateflow.StateFlowActivity

class MainActivity : AppCompatActivity() {


    private val mBtnStateflow: Button by lazy { findViewById<Button>(R.id.btn_stateflow) }
    private val mBtnStatemediator: Button by lazy { findViewById<Button>(R.id.btn_statemediator) }
    private val mBtnMultiplestate: Button by lazy { findViewById<Button>(R.id.btn_multiplestate) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBtnStateflow.setOnClickListener {
            startActivity(Intent(this, StateFlowActivity::class.java))
        }
        mBtnStatemediator.setOnClickListener {
            startActivity(Intent(this, StateFlowMediatorActivity::class.java))
        }
        mBtnMultiplestate.setOnClickListener {
            startActivity(Intent(this, MultipleStateFlowActivity::class.java))
        }

    }
}