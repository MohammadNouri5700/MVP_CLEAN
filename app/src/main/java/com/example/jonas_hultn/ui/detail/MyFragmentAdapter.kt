package com.example.jonas_hultn.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.jonas_hultn.BallonlistQuery

class MyFragmentAdapter(fm: FragmentManager, private val item: BallonlistQuery.Data) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val detail =  DetailFragment()
        detail.item = item.balloons.edges[position]
        return detail
    }

    override fun getCount(): Int {
        return item.balloons.edges.size
    }
}