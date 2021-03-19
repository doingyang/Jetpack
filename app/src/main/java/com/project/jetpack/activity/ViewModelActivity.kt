package com.project.jetpack.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import com.project.jetpack.R
import com.project.jetpack.viewmodel.MyViewModel

class ViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        initEvent()
    }

    private fun initEvent() {
        // 创建ViewModel实例对象
        val viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.startHandler()
    }
}