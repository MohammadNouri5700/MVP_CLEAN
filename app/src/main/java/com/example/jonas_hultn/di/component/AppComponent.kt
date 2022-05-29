package com.example.jonas_hultn.di.component

import com.example.jonas_hultn.di.module.ApolloModule
import com.example.jonas_hultn.di.module.PreferencesModule
import com.example.jonas_hultn.ui.Main.MainActivity
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        PreferencesModule::class,
        ApolloModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)
//    @Component.Factory
//    interface Factory {
//        fun create(@BindsInstance context: Context): AppComponent
//
//    }

}