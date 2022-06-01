package com.example.jonas_hultn.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.jonas_hultn.BallonlistQuery

class MyFragmentAdapter(fm: FragmentManager,val item: BallonlistQuery.Data) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return DetailFragment(item.balloons.edges.get(position))
    }

    override fun getCount(): Int {
        return item.balloons.edges.size
    }
}