package com.example.jonas_hultn.factory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
abstract class BaseActivity:AppCompatActivity() {


    protected lateinit var presenter: Presenter<*>
    protected abstract fun initializePresenter()
    protected abstract fun initializeDagger()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeDagger()
        initializePresenter()
        presenter.initialize(intent.extras)
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onStop() {
        super.onStop()
        presenter.finalizeView()
    }

}