package com.example.jonas_hultn.ui.Main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hepsi.repository.local.DBs.MessageDb
import com.example.jonas_hultn.BallonlistQuery
import com.example.jonas_hultn.R
import com.example.jonas_hultn.databinding.ActivityMainBinding
import com.example.jonas_hultn.factory.BaseActivity
import com.example.jonas_hultn.factory.DaggerMainApplication
import com.example.jonas_hultn.repository.local.callbacks.MessageDao
import com.example.jonas_hultn.ui.detail.DetailSheet
import com.example.jonas_hultn.ui.myballoons.MyBalloon
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, ListImp {


    @Inject
    lateinit var db: MessageDao

    @Inject
    lateinit var mainPresenter: MainPresenter

    lateinit var binding: ActivityMainBinding

    private var adapter: AdapterBalloonList? = null

    private var is_busy = false

    override fun initializePresenter() {
        mainPresenter.setView(this)
        super.presenter = mainPresenter
    }

    override fun initializeDagger() {
        val app = applicationContext as DaggerMainApplication
        app.mainComponent?.inject(this@MainActivity)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)




        mainPresenter._result.observe(
            this
        ) {
            updateList(it)
        }
        binding.shimmer.startShimmer()
        mainPresenter.fetchBalloonList()


        binding.txtMyballons.setOnClickListener{
            startActivity(Intent(this,MyBalloon::class.java))
        }


    }


    private fun updateList(data: BallonlistQuery.Data) {
        if (adapter == null) {
            binding.shimmer.stopShimmer()
            binding.shimmer.visibility = View.GONE
            binding.rec.layoutManager = LinearLayoutManager(this)
            adapter = AdapterBalloonList(data, this, this)
            binding.rec.adapter = adapter;
        } else
            adapter!!.update(data)

        is_busy = false
        binding.rec.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(2)) {
                    if (!is_busy) {
                        is_busy = true
                        mainPresenter.fetchBalloonList()
                    }
                }
            }
        })


    }

    override fun detail(item: BallonlistQuery.Data, position: Int) {
        val detailSheet = DetailSheet(item, position)
        detailSheet.show(supportFragmentManager, "TAG1")
    }

    override fun loadMore(boolean: Boolean) {
        if (boolean)
            binding.prLoad.visibility = View.VISIBLE
        else
            binding.prLoad.visibility = View.GONE

    }


}