package com.suitetwentyfour.bodyframe.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.suitetwentyfour.bodyframe.R
import com.suitetwentyfour.bodyframe.model.User

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val user = intent.extras?.get("user_object") as User
        val billing_button = findViewById<Button>(R.id.button_payment)
        val history_button = findViewById<Button>(R.id.button_history)
        val support_button = findViewById<Button>(R.id.button_support)
        val faq_button = findViewById<Button>(R.id.button_faq)
        val delete_button = findViewById<Button>(R.id.button_delete)
        val logout_button = findViewById<Button>(R.id.button_logout)
        val back_button = findViewById<ImageButton>(R.id.button_back)
        val invite_button = findViewById<Button>(R.id.button_invite)

        if(user.get_user_type() != "TRAINER")
        {
            findViewById<Button>(R.id.button_invite).visibility = View.INVISIBLE
        }

        billing_button.setOnClickListener {
            Toast.makeText(this@SettingsActivity, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }

        history_button.setOnClickListener {
            Toast.makeText(this@SettingsActivity, "Waiting on designs", Toast.LENGTH_SHORT).show()
        }

        support_button.setOnClickListener {
            val intent = Intent(this@SettingsActivity,SupportActivity::class.java)
            intent.putExtra("user_object", user)
            startActivity(intent)
            finish()
        }

        faq_button.setOnClickListener {
            val intent = Intent(this@SettingsActivity,FAQActivity::class.java)
            intent.putExtra("user_object", user)
            startActivity(intent)
            finish()
        }

        delete_button.setOnClickListener {
            Toast.makeText(this@SettingsActivity, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }

        logout_button.setOnClickListener {
            val intent = Intent(this@SettingsActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        back_button.setOnClickListener {
            val intent = Intent(this@SettingsActivity,MainActivity::class.java)
            intent.putExtra("user_object", user)
            startActivity(intent)
            finish()
        }

        invite_button.setOnClickListener {
            val message = "My affiliate code is " + user.get_user_id() + "\nPlease join me with the Body Frame App!\nhttp://www.bodyframe.app"
            val intent = Intent(Intent.ACTION_SENDTO).apply{
                data = Uri.parse("smsto:")
                putExtra("sms_body", message)
            }
            startActivity(intent)
        }
    }
}