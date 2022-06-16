package com.example.jonas_hultn.ui.myballoons


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.jonas_hultn.BallonlistQuery
import com.example.jonas_hultn.R
import com.example.jonas_hultn.factory.Constant
import com.squareup.picasso.Picasso


class AdapterMyBalloonList constructor(
    var data: List<BallonlistQuery.Edge>,
    private val ctx: Context,
) : RecyclerView.Adapter<AdapterMyBalloonList.ViewHolder>() {


    var lastPosition = 0

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtdes: TextView
        val txtname: TextView
        val txtprice: TextView
        val img: ImageView
        init {
            // Define click listener for the ViewHolder's View.
            txtdes = view.findViewById(R.id.txt_des)
            txtname = view.findViewById(R.id.txt_name)
            txtprice = view.findViewById(R.id.txt_price)
            img = view.findViewById(R.id.img)
        }
    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

            return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.row_ballon_list_big, parent, false))
    }


    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.txtname.text = data[position].node.name
        holder.txtdes.text = data[position].node.description
        holder.txtprice.text = data[position].node.price.toString()


        Picasso.with(ctx).load(Constant.DOMAIN + data[position].node.imageUrl)
            .into(holder.img)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(Ndata:  List<BallonlistQuery.Edge>) {
        data=Ndata
        notifyDataSetChanged()
    }
}