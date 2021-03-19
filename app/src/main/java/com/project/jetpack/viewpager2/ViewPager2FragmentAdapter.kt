package com.project.jetpack.viewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.jetpack.fragment.NavigationOneFragment
import com.project.jetpack.fragment.NavigationTwoFragment

/**
 * ViewPager2中新增的FragmentStateAdapter 替代了ViewPager的FragmentStatePagerAdapter跟FragmentPagerAdapter
 */
class ViewPager2FragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    companion object {
        const val HOME_COUNT = 2
        const val PAGE_ONE = 0
        const val PAGE_TWO = 1
        const val PAGE_THREE = 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            PAGE_ONE -> {
                NavigationOneFragment()
            }
            PAGE_TWO -> {
                NavigationTwoFragment()
            }
            else -> NavigationOneFragment()
        }
    }

    override fun getItemCount(): Int {
        return HOME_COUNT
    }
}