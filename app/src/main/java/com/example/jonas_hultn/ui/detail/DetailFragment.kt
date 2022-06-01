package com.example.jonas_hultn.ui.detail

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.jonas_hultn.BallonlistQuery
import com.example.jonas_hultn.R
import com.example.jonas_hultn.factory.Constant
import com.squareup.picasso.Picasso

class DetailFragment(val item: BallonlistQuery.Edge): Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val txt_name = view.findViewById<TextView>(R.id.txt_name)
        val txt_des = view.findViewById<TextView>(R.id.txt_des)
        val img = view.findViewById<ImageView>(R.id.img);

        txt_name.setText(item.node.name.toString())
        txt_des.setText(item.node.description.toString())

        Picasso.with(context).load(Constant.DOMAIN + item.node.imageUrl)
            .into(img)

        return view
    }
}