package com.project.jetpack.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

import com.project.jetpack.R

class MainActivity : AppCompatActivity() {

    private val btnHilt: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.btn_hilt) }
    private val btnDataStore: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.btn_data_store) }
    private val btnNav: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.btn_nav) }
    private val btnViewModel: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.btn_view_model) }
    private val btnLiveData: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.btn_live_data) }
    private val btnRoom: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.btn_room) }
    private val btnCoroutines: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.btn_coroutines) }
    private val btnViewPager2: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.btn_view_pager2) }
    private val btnPaging3: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.btn_paging3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEvent()
    }

    private fun initEvent() {
        btnHilt.setOnClickListener {
            startActivity(Intent(this, HiltActivity::class.java))
        }
        btnDataStore.setOnClickListener {
            startActivity(Intent(this, DataStoreActivity::class.java))
        }
        btnNav.setOnClickListener {
            startActivity(Intent(this, NavigationActivity::class.java))
        }
        btnViewModel.setOnClickListener {
            startActivity(Intent(this, ViewModelActivity::class.java))
        }
        btnLiveData.setOnClickListener {
            startActivity(Intent(this, LiveDataActivity::class.java))
        }
        btnRoom.setOnClickListener {
            startActivity(Intent(this, RoomActivity::class.java))
        }
        btnCoroutines.setOnClickListener {
            startActivity(Intent(this, CoroutinesActivity::class.java))
        }
        btnViewPager2.setOnClickListener {
            startActivity(Intent(this, ViewPager2Activity::class.java))
        }
        btnPaging3.setOnClickListener {
            startActivity(Intent(this, Paging3Activity::class.java))
        }
    }
}