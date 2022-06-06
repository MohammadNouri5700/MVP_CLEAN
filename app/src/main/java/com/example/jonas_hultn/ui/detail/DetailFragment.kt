package com.example.jonas_hultn.ui.detail

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.hepsi.repository.local.DBs.MessageDb
import com.example.jonas_hultn.BallonlistQuery
import com.example.jonas_hultn.R
import com.example.jonas_hultn.factory.BaseFragment
import com.example.jonas_hultn.factory.Constant
import com.example.jonas_hultn.factory.DaggerMainApplication
import com.example.jonas_hultn.repository.local.callbacks.MessageDao
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailFragment(val item: BallonlistQuery.Edge, val db: MessageDao) : Fragment() {

    var gson = Gson()
    var edt_message : EditText?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val txt_name = view.findViewById<TextView>(R.id.txt_name)
        val txt_des = view.findViewById<TextView>(R.id.txt_des)
        val img = view.findViewById<ImageView>(R.id.img);
        val button = view.findViewById<Button>(R.id.btn_save)

        var stm = item.toString()
        edt_message = view.findViewById<EditText>(R.id.edt_message)
        edt_message?.setText(db.getmessages(item.node.id)?.content)

        button.setOnClickListener {
            if (validation(edt_message?.text.toString())) {
                insert_custom_message()
            }else
                Toast.makeText(requireActivity(), "Validation failed", Toast.LENGTH_SHORT).show()
        }

        txt_name.setText(item.node.name.toString())
        txt_des.setText(item.node.description.toString())

        Picasso.with(context).load(Constant.DOMAIN + item.node.imageUrl)
            .into(img)

        return view
    }
    fun insert_custom_message(){
        for (dbitem in db.getmessages()) {
            if (dbitem.msgid.equals(item.node.id)) {
                dbitem.content = edt_message?.text.toString()
                dbitem.dataclass = gson.toJson(item)
                db.update(dbitem)
                return
            }
        }
        db.insert(
            MessageDb(
                0,
                item.node.id,
                edt_message?.text.toString(),
                gson.toJson(item)
            )
        )
    }
    fun validation(string: String): Boolean {
        return string.replace("\\p{C}", "?").length in 11..159
    }
}