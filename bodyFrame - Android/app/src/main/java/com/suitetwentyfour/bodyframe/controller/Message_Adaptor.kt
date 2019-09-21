package com.suitetwentyfour.bodyframe.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suitetwentyfour.bodyframe.R
import com.suitetwentyfour.bodyframe.model.Message
import kotlinx.android.synthetic.main.message_list.view.*

class Message_Adaptor(val items : Array<Message>, val context: Context) : RecyclerView.Adapter<Message_Adaptor.ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.message_list, parent, false)
        return ViewHolder(v)
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    //the class is holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(user: Message) {
            if(user.is_mine())
            {
                itemView.bubble_right.visibility = View.VISIBLE
                itemView.bubble_left.visibility = View.GONE
                itemView.textView_message_right.text = user.get_message()
                val time = user.get_message_date().split(" ")[1].split(":")[0] + ":" +
                        user.get_message_date().split(" ")[1].split(":")[1]
                itemView.textView_time_right.text = time
            }
            else
            {
                itemView.bubble_right.visibility = View.GONE
                itemView.bubble_left.visibility = View.VISIBLE
                itemView.textView_message_left.text = user.get_message()
                val time = user.get_message_date().split(" ")[1].split(":")[0] + ":" +
                        user.get_message_date().split(" ")[1].split(":")[1]
                itemView.textView_time_left.text = time
            }
        }
    }
}