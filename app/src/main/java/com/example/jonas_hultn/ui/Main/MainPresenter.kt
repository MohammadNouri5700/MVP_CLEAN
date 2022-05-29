package com.example.jonas_hultn.ui.Main

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.jonas_hultn.BallonlistQuery
import com.example.jonas_hultn.factory.Presenter
import com.example.jonas_hultn.repository.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainPresenter @Inject constructor(private val repository: RemoteDataSource) :
    Presenter<MainContract.View>(), MainContract.Presenter {

    val _result = MutableLiveData<BallonlistQuery.Data>()


    private var parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    override fun initialize(extras: Bundle?) {
        super.initialize(extras)
    }

     fun fetchBalloonList() {

        getView()?.loadMore(true)
        scope.launch {
            repository.getBallonList().apply {
                getView()?.loadMore(false)
                if (data == null)
                    fetchBalloonList()
                else
                    _result.postValue(data)
            }
        }
    }


}