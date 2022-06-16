package com.example.jonas_hultn.factory


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


/**
 * Created by AhmedEltaher on 5/12/2016
 */


abstract class BaseFragment : Fragment(), BaseView {

    protected var presenter: Presenter<*>? = null

    protected abstract fun initializeDagger()

    protected abstract fun initializePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeDagger()
        initializePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter?.initialize(arguments)
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter?.start()
    }

    override fun onStop() {
        super.onStop()
        presenter?.finalizeView()
    }
}