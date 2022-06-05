package com.example.jonas_hultn.ui.myballoons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.hepsi.repository.local.DBs.MessageDb
import com.example.jonas_hultn.BallonlistQuery
import com.example.jonas_hultn.R
import com.example.jonas_hultn.databinding.ActivityMyBalloonBinding
import com.example.jonas_hultn.factory.BaseActivity
import com.example.jonas_hultn.factory.DaggerMainApplication
import com.example.jonas_hultn.repository.local.callbacks.MessageDao
import com.example.jonas_hultn.ui.detail.MyFragmentAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import javax.inject.Inject

class MyBalloon : BaseActivity() , MyBalloonContract.View{



    @Inject
    lateinit var ballonPresenter: MyBalloonPresenter

    @Inject
    lateinit var db: MessageDao

    lateinit var binding:ActivityMyBalloonBinding


    private var adapter: AdapterMyBalloonList? = null



    override fun initializePresenter() {
        ballonPresenter.setView(this)
        super.presenter = ballonPresenter
    }

    override fun initializeDagger() {
        val app = applicationContext as DaggerMainApplication
        app.mainComponent?.inject(this@MyBalloon)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MyBalloon, R.layout.activity_my_balloon)


        ballonPresenter.load()

        ballonPresenter._result.observe(
            this
        ) {
            updateList(it)
        }

    }

    private fun updateList(it: List<BallonlistQuery.Edge>?) {
        if (adapter == null) {
            binding.rec.layoutManager = LinearLayoutManager(this)
            adapter = AdapterMyBalloonList(it!!, this)
            binding.rec.adapter = adapter;
        } else
            adapter!!.update(it!!)
    }


}