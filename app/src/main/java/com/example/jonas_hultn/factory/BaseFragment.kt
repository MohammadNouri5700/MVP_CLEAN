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

    abstract val layoutId: Int

    private val toolbarTitleKey: String? = null

    protected abstract fun initializeDagger()

    protected abstract fun initializePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeDagger()
        initializePresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.initialize(arguments)


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
