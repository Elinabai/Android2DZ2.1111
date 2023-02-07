package com.geektech.android2dz21.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.geektech.android2dz21.ui.fragments.onboard.OnBoardPagingFragment

class OnBoardViewPagerAdapters(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = OnBoardPagingFragment().apply {
        arguments = Bundle().apply {
            putInt(OnBoardPagingFragment.ARG_ONBOARD_PAGE_POSITION, position)
        }
    }
}