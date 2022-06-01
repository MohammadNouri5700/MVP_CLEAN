package com.example.jonas_hultn.ui.Main


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jonas_hultn.BallonlistQuery
import com.example.jonas_hultn.R
import com.example.jonas_hultn.factory.Constant
import com.squareup.picasso.Picasso


class AdapterBalloonList constructor(
    var data: BallonlistQuery.Data,
    private val ctx: Context,
    private val listener: ListImp
) : RecyclerView.Adapter<AdapterBalloonList.ViewHolder>() {


    var lastPosition = 0

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtdes: TextView
        val txtname: TextView
        val txtprice: TextView
        val txtdetail: TextView
        val img: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            txtdes = view.findViewById(R.id.txt_des)
            txtname = view.findViewById(R.id.txt_name)
            txtprice = view.findViewById(R.id.txt_price)
            txtdetail = view.findViewById(R.id.txt_detail)
            img = view.findViewById(R.id.img)

        }
    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

            return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.row_ballon_list, parent, false))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(newdata: BallonlistQuery.Data) {
        data = newdata
        this.notifyDataSetChanged()
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.txtname.text = data.balloons.edges[position].node.name
        holder.txtdes.text = data.balloons.edges[position].node.description
        holder.txtprice.text = data.balloons.edges[position].node.price.toString()

        holder.txtdetail.setOnClickListener {
            data.balloons.edges.get(position).let { it1 -> listener.detail(data,position) }
        }

        Picasso.with(ctx).load(Constant.DOMAIN + data.balloons.edges[position].node.imageUrl)
            .into(holder.img)

    }

    override fun getItemCount(): Int {
        return data.balloons.edges.size
    }
}