package com.project.jetpack.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

import com.project.jetpack.R
import com.project.jetpack.viewpager2.MyViewPager2Adapter
import com.project.jetpack.viewpager2.ScaleInTransformer
import com.project.jetpack.viewpager2.ViewPager2FragmentAdapter

class ViewPager2Activity : AppCompatActivity(), View.OnClickListener {

    private val tabLayout: TabLayout by lazy { findViewById<TabLayout>(R.id.tab_layout) }
    private val viewPager2: ViewPager2 by lazy { findViewById<ViewPager2>(R.id.view_pager2) }
    private val btnFakeDrag: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.btn_fake_drag) }
    private val btnFragOne: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.btn_frag_one) }
    private val btnFragTwo: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.btn_frag_two) }

    private val list = arrayListOf<Int>(Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)
        initEvent()
    }

    private fun initEvent() {
        /*val adapter = MyViewPager2Adapter()
        adapter.setData(list)
        viewPager2.adapter = adapter*/

        // 配合Fragment使用
        viewPager2.adapter = ViewPager2FragmentAdapter(this)
//        viewPager2.isUserInputEnabled = false
        // 配合TabLayout使用
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = "Tab ${(position + 1)}"
        }.attach()

//        // 支持垂直分页   android:orientation="vertical"
//        viewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL
//
//        // 支持从右到左   android:layoutDirection="rtl"
//        viewPager2.layoutDirection = LayoutDirection.RTL
//
//        // 禁止滑动
//        viewPager2.isUserInputEnabled = false
//
//        // 切换到某个位置
//        viewPager2.currentItem = 1
//
//        // ViewPager2中offScreenPageLimit的默认值被设置为了-1，当offScreenPageLimit为-1的时候，使用的是RecyclerView的缓存机制。而当offScreenPageLimit大于1时，才会去实现预加载。
//        viewPager2.offscreenPageLimit = 1
//
//        // 给页面设置间距
//        viewPager2.setPageTransformer(MarginPageTransformer(130))

        // 扩屏的效果：我们可以通过给ViewPager2里的RecyclerView设置Padding来实现
        viewPager2.apply {
            offscreenPageLimit = 1
            val recyclerView = getChildAt(0) as RecyclerView
            recyclerView.apply {
                val padding =
                    resources.getDimensionPixelOffset(R.dimen.design_bottom_navigation_icon_size)
                setPadding(padding, 0, padding, 0)
                clipToPadding = false
            }
        }

        // 给页面之间设置跳转动画
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(ScaleInTransformer())
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        viewPager2.setPageTransformer(compositePageTransformer)


        // 滑动监听
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.i("TAG", "onPageSelected: $position")
            }
        })


        btnFakeDrag.setOnClickListener(this)
        btnFragOne.setOnClickListener(this)
        btnFragTwo.setOnClickListener(this)
    }

    private fun fakeDrag() {
        // 可以通过fakeDragBy来移动;
        // 负数表示向下一个页面滑动，正数表示向前一个页面滑动;
        // 使用fakeDragBy前需要先调用beginFakeDrag方法才能生效;
        // 可以使用endFakeDrag停止.
        viewPager2.beginFakeDrag()
        viewPager2.fakeDragBy(-500f)

        if (viewPager2.fakeDragBy(-300f)) {
            viewPager2.endFakeDrag()
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            btnFakeDrag -> fakeDrag()
            btnFragOne -> viewPager2.setCurrentItem(0, false)
            btnFragTwo -> viewPager2.setCurrentItem(1, false)
        }
    }
}