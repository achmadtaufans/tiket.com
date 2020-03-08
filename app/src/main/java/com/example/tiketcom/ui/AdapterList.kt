package com.example.tiketcom.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.tiketcom.R
import com.example.tiketcom.view_model.ItemList

/**
 * adapter list to showing list of github user list
 */
class Adapter(items: List<ItemList>, context: Context) :
    RecyclerView.Adapter<Adapter.MyViewHolder?>() {
    private val items: List<ItemList> = items
    private val context: Context = context

    /**
     * set layout whenever adapter created
     */
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(view)
    }

    /**
     * binding layout
     */
    override fun onBindViewHolder(@NonNull holder: MyViewHolder, position: Int) {
        holder.name.setText(items[position].getName())
        Glide.with(context)
            .load(items[position].getAvatar())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.avatar)
    }

    /**
     * get count github user list
     */
    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * set name, and photo and implement to adapter UI
     */
    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var avatar: ImageView = itemView.findViewById(R.id.avatar)
    }
}
