package com.project.jetpack.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.project.jetpack.R
import com.project.jetpack.livedata.NetworkLiveData
import com.project.jetpack.viewmodel.LiveDataViewModel

class LiveDataActivity : AppCompatActivity() {

    private val tv: AppCompatTextView by lazy { findViewById<AppCompatTextView>(R.id.tv) }
    private val btn: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.btn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
        initEvent()
    }

    private fun initEvent() {
        val vm = ViewModelProvider(this).get(LiveDataViewModel::class.java)
        var i = 0
        btn.setOnClickListener {
            // setValue()方法用来在主线程中更新数据
            vm.getData().value = i++.toString()
            // postValue()方法用来在子线程中更新数据
//            vm.getData().postValue(i++.toString())
        }

        vm.getData().observe(this, Observer<String> {
            tv.text = it ?: "null"
        })

        NetworkLiveData(this).observe(this, Observer {
            tv.text = it ?: "null"
        })
    }
}