package com.suitetwentyfour.bodyframe.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suitetwentyfour.bodyframe.R
import com.suitetwentyfour.bodyframe.model.Client
import com.suitetwentyfour.bodyframe.model.Notification
import kotlinx.android.synthetic.main.client_list.view.*
import kotlinx.android.synthetic.main.notification_list.view.*
import kotlinx.android.synthetic.main.client_list.view.textView_name as textView_name1

class Notification_Adaptor(val items : Array<Notification>, val context: Context) : RecyclerView.Adapter<Notification_Adaptor.ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.notification_list, parent, false)
        return ViewHolder(v)
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    //the class is holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(user: Notification) {
            var name = user.get_notification().split(";")[0]
            var message = user.get_notification().split(";")[1]
            val time = user.get_notification_date().split(" ")[1].split(":")[0] + ":" +
                    user.get_notification_date().split(" ")[1].split(":")[1]
            itemView.textView_name.text = name
            itemView.textView_notification.text = message
            itemView.textView_time.text = time
        }
    }
}