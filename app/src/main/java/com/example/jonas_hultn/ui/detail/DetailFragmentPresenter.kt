package com.example.jonas_hultn.ui.detail

import com.example.hepsi.repository.local.DBs.MessageDb
import com.example.jonas_hultn.BallonlistQuery
import com.example.jonas_hultn.factory.Presenter
import com.example.jonas_hultn.repository.local.callbacks.MessageDao
import com.example.jonas_hultn.ui.Main.MainContract
import com.google.gson.Gson
import javax.inject.Inject

class DetailFragmentPresenter @Inject constructor() :
    Presenter<DetailFragmentContract.View>(), DetailFragmentContract.Presenter {


    @Inject
    lateinit var db: MessageDao


    @Inject
    lateinit var gson: Gson

    fun insertCustomMessage(item: BallonlistQuery.Edge, message: String) {
        if (checkData(item)){
            val item = db.getmessages(item.node.id)
            item.content = message
            item.dataclass = gson.toJson(item)
            db.update(item)
        }

        db.insert(
            MessageDb(
                0,
                item.node.id,
                message,
                gson.toJson(item)
            )
        )
    }

    private fun checkData(item: BallonlistQuery.Edge):Boolean{
        return db.getmessages().any { it.msgid == item.node.id }
    }

    fun validation(string: String): Boolean {
        return string.replace("\\p{C}", "?").length in 11..159
    }

}