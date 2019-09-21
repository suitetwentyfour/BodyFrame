package com.suitetwentyfour.bodyframe.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suitetwentyfour.bodyframe.R
import com.suitetwentyfour.bodyframe.model.Activity
import com.suitetwentyfour.bodyframe.model.Client
import com.suitetwentyfour.bodyframe.model.Notification
import kotlinx.android.synthetic.main.activity_list.view.*
import kotlinx.android.synthetic.main.client_list.view.*
import kotlinx.android.synthetic.main.notification_list.view.*

class Activity_Adaptor(val items : Array<Activity>, val context: Context) : RecyclerView.Adapter<Activity_Adaptor.ViewHolder>() {

    var onItemClick: ((Activity) -> Unit)? = null
    var onDoneClick: ((Activity) -> Unit)? = null

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_list, parent, false)
        return ViewHolder(v)
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    //the class is holding the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(user: Activity) {
            itemView.textView_activity_name.text = user.get_activity_description()
            itemView.radioButton_done.setChecked(user.get_completed() == "true")
            itemView.textView_activity_brief.text = user.get_activity_name()
            itemView.textView_activity_time_calorie.text = user.get_activity_time() + " minutes," + user.get_calories() + " cal"

            itemView.imageView_activity.setOnClickListener { onItemClick?.invoke(items[adapterPosition]) }
            itemView.radioButton_done.setOnClickListener { onDoneClick?.invoke(items[adapterPosition]) }
        }
    }
}