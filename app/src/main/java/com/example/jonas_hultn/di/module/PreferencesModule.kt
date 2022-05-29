package com.example.jonas_hultn.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import javax.inject.Inject

@Module
class PreferencesModule @Inject constructor(val context: Context) {

    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    private var prefName: String = "testJones" //TODO Create constant class and inherit from there

    fun set(key: String,value:String){
        val sharedPreferences: SharedPreferences =  context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }
    fun get(key: String): String? {
        val sharedPreference =
            context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        return sharedPreference.getString(key, "")
    }


}