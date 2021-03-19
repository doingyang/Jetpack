package com.project.jetpack.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.project.jetpack.R
import com.project.jetpack.viewmodel.CoroutinesViewModel

class CoroutinesActivity : AppCompatActivity() {

    private val tv: AppCompatTextView by lazy { findViewById<AppCompatTextView>(R.id.tv) }
    private val btn: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.btn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        initEvent()
    }

    private fun initEvent() {
        val vm = ViewModelProvider(this).get(CoroutinesViewModel::class.java)
        vm.nameLiveData.observe(this) {
            tv.text = it
        }
        btn.setOnClickListener {
            vm.checkName()
            // 证明UI没有被阻塞
            tv.text = "即将登场的是："
        }
    }
}