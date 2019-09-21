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
import kotlinx.android.synthetic.main.faq_list.view.*
import kotlinx.android.synthetic.main.notification_list.view.*
import kotlinx.android.synthetic.main.client_list.view.textView_name as textView_name1

class FAQ_Adaptor(val items : Array<String>, val context: Context) : RecyclerView.Adapter<FAQ_Adaptor.ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.faq_list, parent, false)
        return ViewHolder(v)
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    //the class is holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(user: String) {
            var question = user.split(";")[0]
            var answer = user.split(";")[1]
            itemView.textView_question.text = question
            itemView.textView_answer.text = answer
        }
    }
}