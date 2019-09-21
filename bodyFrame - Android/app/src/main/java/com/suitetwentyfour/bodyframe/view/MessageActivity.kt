package com.suitetwentyfour.bodyframe.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suitetwentyfour.bodyframe.R
import com.suitetwentyfour.bodyframe.controller.API_Controller
import com.suitetwentyfour.bodyframe.controller.Message_Adaptor
import com.suitetwentyfour.bodyframe.controller.Notification_Adaptor
import com.suitetwentyfour.bodyframe.model.Client
import com.suitetwentyfour.bodyframe.model.User
import java.lang.Boolean.TRUE

class MessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        val test = API_Controller()
        val user = intent.extras?.get("user_object") as User
        val messagee = intent.extras?.get("messagee") as String
        val profile = test.Get_Profile(messagee)
        val back_button = findViewById<ImageButton>(R.id.button_back)
        val send_button = findViewById<TextView>(R.id.textView_send)

        findViewById<TextView>(R.id.textView_user_name).text = profile.get_first_name() + " " + profile.get_last_name()

        fun createView() {
            val message_list = test.Get_Messages(user.get_user_id(), messagee)
            findViewById<RecyclerView>(R.id.recyclerView_messages).layoutManager = LinearLayoutManager(this@MessageActivity)
            findViewById<RecyclerView>(R.id.recyclerView_messages).adapter = Message_Adaptor(message_list, this@MessageActivity)
            findViewById<RecyclerView>(R.id.recyclerView_messages).getLayoutManager()?.scrollToPosition(message_list.size - 1)
        }

        createView()

        send_button.setOnClickListener {
            if(findViewById<EditText>(R.id.editText_message).text.toString() == "")
            {
                Toast.makeText(this@MessageActivity, "You cannot send a blank message.", Toast.LENGTH_SHORT).show()
            }
            else
            {
                test.Send_Message(user.get_user_id(), messagee, findViewById<EditText>(R.id.editText_message).text.toString())
                findViewById<EditText>(R.id.editText_message).setText("")
                createView()
            }
        }

        back_button.setOnClickListener {
            if(user.get_user_type() == "TRAINER")
            {
                try
                {
                    val client = intent.extras?.get("client_object") as Client
                    val intent = Intent(this@MessageActivity, ClientActivity::class.java)
                    intent.putExtra("user_object", user)
                    intent.putExtra("client_object", client)
                    startActivity(intent)
                    finish()
                }
                catch(ex :Exception)
                {
                    val intent = Intent(this@MessageActivity, MessagesActivity::class.java)
                    intent.putExtra("user_object", user)
                    startActivity(intent)
                    finish()
                }
            }
            else
            {
                val intent = Intent(this@MessageActivity, MainActivity::class.java)
                intent.putExtra("user_object", user)
                startActivity(intent)
                finish()
            }
        }
    }
}