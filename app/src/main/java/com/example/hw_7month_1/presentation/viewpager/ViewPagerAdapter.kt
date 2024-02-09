package com.example.hw_7month_1.presentation.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hw_7month_1.presentation.camers.CamerasFragment
import com.example.hw_7month_1.presentation.doors.DoorsFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            CamerasFragment()
        else
            DoorsFragment()
    }
}