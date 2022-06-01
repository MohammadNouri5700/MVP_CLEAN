package com.example.jonas_hultn.ui.Main

import com.example.jonas_hultn.BallonlistQuery

interface ListImp {
    fun detail(item: BallonlistQuery.Data,position:Int)
}