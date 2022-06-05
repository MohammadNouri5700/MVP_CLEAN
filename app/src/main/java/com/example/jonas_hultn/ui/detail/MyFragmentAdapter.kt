package com.example.jonas_hultn.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.jonas_hultn.BallonlistQuery
import com.example.jonas_hultn.repository.local.callbacks.MessageDao

class MyFragmentAdapter(fm: FragmentManager,val item: BallonlistQuery.Data,val db: MessageDao) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return DetailFragment(item.balloons.edges.get(position),db)
    }

    override fun getCount(): Int {
        return item.balloons.edges.size
    }
}