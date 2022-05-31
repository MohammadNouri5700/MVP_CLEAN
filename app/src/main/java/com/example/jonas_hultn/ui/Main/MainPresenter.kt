package com.example.jonas_hultn.ui.Main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo3.exception.ApolloException
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
    private val _result_temp: ArrayList<BallonlistQuery.Edge> = ArrayList()


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
            try {
                if (_result.value == null) {
                    val res = repository.getBallonList("")
                    getView()?.loadMore(false)
                    if (res.data == null)
                        fetchBalloonList()
                    else {
                        res.data?.balloons?.edges?.let { it1 -> _result_temp.addAll(it1) }
                        _result.postValue(res.data)
                    }
                } else {
                    if (_result.value?.balloons?.pageInfo?.endCursor==null){
                        getView()?.loadMore(false)
                    }
                    _result.value?.balloons?.pageInfo?.endCursor?.let {
                        val res = repository.getBallonList(it)
                        getView()?.loadMore(false)
                        if (res.data == null)
                            fetchBalloonList()
                        else {
                            res.data?.balloons?.edges?.let { it1 -> _result_temp.addAll(it1) }
                            val dats: BallonlistQuery.Data = BallonlistQuery.Data(
                                BallonlistQuery.Balloons(
                                    res.data!!.balloons.__typename,
                                    res.data!!.balloons.pageInfo,
                                    _result_temp
                                )
                            )
                            _result.postValue(dats)

                        }
                    }
                }
            } catch (exception: ApolloException) {
                Log.e("App", exception.message.toString())
            }

        }
    }


}