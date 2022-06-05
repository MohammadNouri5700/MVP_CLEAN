package com.example.jonas_hultn.di.component

import com.example.jonas_hultn.di.module.ApolloModule
import com.example.jonas_hultn.di.module.DataBaseModule
import com.example.jonas_hultn.di.module.PreferencesModule
import com.example.jonas_hultn.di.module.PresenterModule
import com.example.jonas_hultn.ui.Main.MainActivity
import com.example.jonas_hultn.ui.myballoons.MyBalloon
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        PreferencesModule::class,
        ApolloModule::class,
        DataBaseModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: MyBalloon)
//    fun inject(detailFragment: DetailFragment)
//    @Component.Factory
//    interface Factory {
//        fun create(@BindsInstance context: Context): AppComponent
//
//    }

}