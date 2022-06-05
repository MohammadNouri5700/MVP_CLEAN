package com.example.jonas_hultn.ui.myballoons

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.jonas_hultn.BallonlistQuery
import com.example.jonas_hultn.factory.Presenter
import com.example.jonas_hultn.repository.local.callbacks.MessageDao
import com.example.jonas_hultn.ui.Main.MainContract
import com.google.gson.Gson
import javax.inject.Inject

class MyBalloonPresenter @Inject constructor(val db: MessageDao) :
    Presenter<MyBalloonContract.View>(), MyBalloonContract.Presenter {


    val _result = MutableLiveData<List<BallonlistQuery.Edge>>()


    override fun initialize(extras: Bundle?) {
        super.initialize(extras)
    }


    fun load() {
        val gson = Gson()
        val result: ArrayList<BallonlistQuery.Edge> = ArrayList()
        for (item in db.getmessages()){
            result.add(gson.fromJson(item.dataclass,BallonlistQuery.Edge::class.java))
        }
        _result.postValue(result.toList())
    }


}