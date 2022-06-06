package com.example.jonas_hultn.ui.detail

import com.example.jonas_hultn.factory.Presenter
import com.example.jonas_hultn.repository.local.callbacks.MessageDao
import com.example.jonas_hultn.ui.Main.MainContract
import javax.inject.Inject

class DetailFragmentPresenter @Inject constructor(db: MessageDao)
    : Presenter<DetailFragmentContract.View>(), DetailFragmentContract.Presenter {




}