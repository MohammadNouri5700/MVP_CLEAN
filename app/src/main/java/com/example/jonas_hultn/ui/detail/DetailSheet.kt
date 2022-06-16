package com.example.jonas_hultn.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.jonas_hultn.BallonlistQuery
import com.example.jonas_hultn.R
import com.example.jonas_hultn.repository.local.callbacks.MessageDao
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailSheet constructor(val item: BallonlistQuery.Data,val position:Int) : BottomSheetDialogFragment() {

    private lateinit var bottomSheet: ViewGroup
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private lateinit var viewPager: ViewPager
    private lateinit var appBarLayout: AppBarLayout

    override fun onStart() {
        super.onStart()
        bottomSheet =
            dialog!!.findViewById(com.google.android.material.R.id.design_bottom_sheet) as ViewGroup // notice the R root package
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(view: View, i: Int) {
                if (BottomSheetBehavior.STATE_HIDDEN == i) {
                    dismiss() // destroy the instance
                }
            }

            override fun onSlide(view: View, v: Float) {}
        })

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun hideAppBar(view: View) {
        val params = view.layoutParams
        params.height = 0
        view.layoutParams = params
    }

    private fun showView(view: View, size: Int) {
        val params = view.layoutParams
        params.height = size
        view.layoutParams = params
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myview: View = inflater.inflate(R.layout.bottomsheet, container, false)
        viewPager = myview.findViewById(R.id.myViewPager)
        val adapter = MyFragmentAdapter(childFragmentManager,item)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(position,false)
        return myview
    }
}