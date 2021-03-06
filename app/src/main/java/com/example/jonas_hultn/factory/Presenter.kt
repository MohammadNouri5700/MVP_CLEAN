package com.example.jonas_hultn.factory

import android.os.Bundle
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicBoolean

abstract class Presenter<T : BaseView> {

    private var view: WeakReference<T>? = null

    protected var isViewAlive = AtomicBoolean()

    fun getView(): T? {
        return view?.get()
    }

    fun setView(view: T) {
        this.view = WeakReference(view)
    }


    open fun initialize(extras: Bundle?) {}

    fun start() {
        isViewAlive.set(true)
    }

    fun finalizeView() {
        isViewAlive.set(false)
    }

}

