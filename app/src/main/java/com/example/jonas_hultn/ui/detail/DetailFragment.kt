package com.example.jonas_hultn.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.jonas_hultn.BallonlistQuery
import com.example.jonas_hultn.databinding.FragmentDetailBinding
import com.example.jonas_hultn.factory.BaseFragment
import com.example.jonas_hultn.factory.Constant
import com.example.jonas_hultn.factory.DaggerMainApplication
import com.example.jonas_hultn.repository.local.callbacks.MessageDao
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailFragment @Inject constructor() : BaseFragment() {


    lateinit var item: BallonlistQuery.Edge


    @Inject
    lateinit var detailPresenter: DetailFragmentPresenter

    @Inject
    lateinit var db: MessageDao



    private lateinit var binding: FragmentDetailBinding

    override fun initializeDagger() {
        val app = activity?.applicationContext as DaggerMainApplication
        app.mainComponent?.inject(this)
    }

    override fun initializePresenter() {
        super.presenter = detailPresenter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentDetailBinding.inflate(inflater,container ,false)




        binding.btnSave.setOnClickListener {
            if (detailPresenter.validation(binding.edtMessage.text.toString())) {
                detailPresenter.insertCustomMessage(item,binding.edtMessage.text.toString())
            }else
                Toast.makeText(requireActivity(), "Validation failed", Toast.LENGTH_SHORT).show()
        }


        initView()


        return binding.root
    }

    private fun initView(){
        binding.txtName.text = item.node.name
        binding.txtDes.text = item.node.description
        Picasso.with(context).load(Constant.DOMAIN + item.node.imageUrl)
            .into(binding.img)
        binding.edtMessage.setText(db.getmessages(item.node.id)?.content)
    }


}