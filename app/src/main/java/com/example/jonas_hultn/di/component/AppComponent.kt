package com.example.jonas_hultn.di.component

import com.example.jonas_hultn.di.module.*
import com.example.jonas_hultn.ui.Main.MainActivity
import com.example.jonas_hultn.ui.detail.DetailFragment
import com.example.jonas_hultn.ui.myballoons.MyBalloon
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        PreferencesModule::class,
        ServiceModule::class,
        ApolloModule::class,
        DataBaseModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: MyBalloon)
    fun inject(fragment: DetailFragment)
//    fun inject(detailFragment: DetailFragment)
//    @Component.Factory
//    interface Factory {
//        fun create(@BindsInstance context: Context): AppComponent
//
//    }

}