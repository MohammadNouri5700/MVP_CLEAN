package com.example.jonas_hultn.ui.Main

import com.example.jonas_hultn.factory.BaseView

interface MainContract :BaseView {


    interface View : BaseView {
        fun loadMore(boolean: Boolean)
    }


    interface Presenter {


    }



}