package com.suitetwentyfour.bodyframe.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suitetwentyfour.bodyframe.R
import com.suitetwentyfour.bodyframe.controller.API_Controller
import com.suitetwentyfour.bodyframe.controller.Messages_Adaptor
import com.suitetwentyfour.bodyframe.model.User

class MessagesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

        val test = API_Controller()
        val user = intent.extras?.get("user_object") as User
        val back_button = findViewById<ImageButton>(R.id.button_back)

        if(user.get_user_type() == "TRAINER")
        {
            //Get Messages List
            val client_list = test.Get_Contact_List(user.get_user_id())
            val adaptor = Messages_Adaptor(client_list, this@MessagesActivity)
            findViewById<RecyclerView>(R.id.recyclerView_List).layoutManager = LinearLayoutManager(this@MessagesActivity)
            findViewById<RecyclerView>(R.id.recyclerView_List).adapter = adaptor
            adaptor.onItemClick = {
                val intent = Intent(this@MessagesActivity,MessageActivity::class.java)
                intent.putExtra("user_object", user)
                intent.putExtra("messagee", it.get_user_id())
                startActivity(intent)
                finish()
            }
        }
        else
        {
            val intent = Intent(this@MessagesActivity,MessageActivity::class.java)
            intent.putExtra("user_object", user)
            intent.putExtra("messagee", user.get_assigned_to())
            startActivity(intent)
            finish()
        }

        back_button.setOnClickListener {
            val intent = Intent(this@MessagesActivity, MainActivity::class.java)
            intent.putExtra("user_object", user)
            startActivity(intent)
            finish()
        }
    }
}