package com.example.jonas_hultn.factory

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.VisibleForTesting
import com.example.jonas_hultn.di.component.AppComponent
import com.example.jonas_hultn.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class DaggerMainApplication :  Application() {
    var mainComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerAppComponent.create()
        context = applicationContext
    }

    @VisibleForTesting
    fun setComponent(mainComponent: AppComponent) {
        this.mainComponent = mainComponent
    }
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        fun getDrawableById(resId: Int): Drawable {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                context.resources.getDrawable(resId,context.theme)
            else
                context.resources.getDrawable(resId)
        }
    }


}