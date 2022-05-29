package com.example.jonas_hultn.ui.Main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jonas_hultn.BallonlistQuery
import com.example.jonas_hultn.R
import com.example.jonas_hultn.databinding.ActivityMainBinding
import com.example.jonas_hultn.factory.BaseActivity
import com.example.jonas_hultn.factory.DaggerMainApplication
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, ListImp {


    @Inject
    lateinit var mainPresenter: MainPresenter

    lateinit var binding: ActivityMainBinding


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
    }

    private fun updateList(data: BallonlistQuery.Data) {
        binding.rec.layoutManager = LinearLayoutManager(this)
        binding.rec.adapter = AdapterBalloonList(data, this, this)
        binding.shimmer.stopShimmer()
        binding.shimmer.visibility = View.GONE
    }

    override fun detail(item: BallonlistQuery.Edge) {
        Toast.makeText(this, "impl", Toast.LENGTH_SHORT).show()
    }

    override fun loadMore(boolean: Boolean) {
        if (boolean)
            binding.prLoad.visibility =View.VISIBLE
        else
            binding.prLoad.visibility =View.GONE

    }


}