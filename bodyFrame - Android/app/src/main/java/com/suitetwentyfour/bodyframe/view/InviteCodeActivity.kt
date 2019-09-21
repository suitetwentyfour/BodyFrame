package com.suitetwentyfour.bodyframe.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.suitetwentyfour.bodyframe.R

class InviteCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite_code)

        val user_type = intent.getStringExtra("User_Type")
        val full_name = intent.getStringExtra("Full_Name")
        val birthday = intent.getStringExtra("Birthday")
        val address = intent.getStringExtra("Address")
        val phone_number = intent.getStringExtra("Phone_Number")
        val continue_button = findViewById<Button>(R.id.button_continue)
        val back_button = findViewById<Button>(R.id.button_back)

        continue_button.setOnClickListener {
            if(findViewById<EditText>(R.id.editText_invite_code).text.toString() == "")
            {
                Toast.makeText(this@InviteCodeActivity, "Invite code cannot be blank.", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val intent :Intent
                // Add code to validate the invite code
                if(user_type == "TRAINER") { intent = Intent(this@InviteCodeActivity,CredentialActivity::class.java) }
                else { intent = Intent(this@InviteCodeActivity,GoalActivity::class.java) }
                intent.putExtra("User_Type",user_type)
                intent.putExtra("Full_Name",full_name)
                intent.putExtra("Birthday",birthday)
                intent.putExtra("Address",address)
                intent.putExtra("Phone_Number",phone_number)
                intent.putExtra("Trainer_Code", findViewById<EditText>(R.id.editText_invite_code).text.toString())
                startActivity(intent)
                finish()
            }
        }

        back_button.setOnClickListener {
            val intent = Intent(this@InviteCodeActivity,SignUpActivity::class.java)
            intent.putExtra("User_Type",user_type)
            intent.putExtra("Full_Name",full_name)
            intent.putExtra("Birthday",birthday)
            intent.putExtra("Address",address)
            intent.putExtra("Phone_Number",phone_number)
            startActivity(intent)
            finish()
        }
    }
}