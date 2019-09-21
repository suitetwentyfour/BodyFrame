package com.suitetwentyfour.bodyframe.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.suitetwentyfour.bodyframe.R
import com.suitetwentyfour.bodyframe.model.Client
import kotlinx.android.synthetic.main.client_list.view.*

class Client_Adaptor(val items : Array<Client>, val context: Context) : RecyclerView.Adapter<Client_Adaptor.ViewHolder>() {

    var onItemClick: ((Client) -> Unit)? = null

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.client_list, parent, false)
        return ViewHolder(v)
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    //the class is holding the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(user: Client) {
            itemView.textView_name.text = user.get_first_name() + " " + user.get_last_name()
            itemView.textView_name.setOnClickListener { onItemClick?.invoke(items[adapterPosition]) }
            itemView.imageView_client.setOnClickListener { onItemClick?.invoke(items[adapterPosition]) }
        }
    }
}