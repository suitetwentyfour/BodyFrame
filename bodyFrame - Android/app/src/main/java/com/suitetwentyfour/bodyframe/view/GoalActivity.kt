package com.suitetwentyfour.bodyframe.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.suitetwentyfour.bodyframe.R

class GoalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        val user_type = intent.getStringExtra("User_Type")
        val full_name = intent.getStringExtra("Full_Name")
        val birthday = intent.getStringExtra("Birthday")
        val address = intent.getStringExtra("Address")
        val phone_number = intent.getStringExtra("Phone_Number")
        val trainer_code = intent.getStringExtra("Trainer_Code")
        val login_button = findViewById<TextView>(R.id.textView_login)
        val continue_button = findViewById<Button>(R.id.button_continue)
        val back_button = findViewById<Button>(R.id.button_back)

        login_button.setOnClickListener {
            val intent = Intent(this@GoalActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        continue_button.setOnClickListener {
            val sex = findViewById<Spinner>(R.id.spinner_sex).getSelectedItem().toString()
            val goal = findViewById<Spinner>(R.id.spinner_goal).getSelectedItem().toString()
            val weight = findViewById<EditText>(R.id.editText_weight).text.toString()
            val height = findViewById<EditText>(R.id.editText_height).text.toString()
            if(sex == "" || goal == "" || weight == "" || height == "")
            {
                if(sex == "")
                { Toast.makeText(this@GoalActivity, "Gender cannot be unselected.", Toast.LENGTH_SHORT).show() }
                else if(goal == "")
                { Toast.makeText(this@GoalActivity, "Goal cannot be unselected.", Toast.LENGTH_SHORT).show() }
                else if(weight == "")
                { Toast.makeText(this@GoalActivity, "Weight cannot be blank.", Toast.LENGTH_SHORT).show() }
                else if(height == "")
                { Toast.makeText(this@GoalActivity, "Height cannot be blank.", Toast.LENGTH_SHORT).show() }
            }
            else
            {
                val intent = Intent(this@GoalActivity,CredentialActivity::class.java)
                intent.putExtra("User_Type",user_type)
                intent.putExtra("Full_Name",full_name)
                intent.putExtra("Birthday",birthday)
                intent.putExtra("Address",address)
                intent.putExtra("Phone_Number",phone_number)
                intent.putExtra("Trainer_Code", trainer_code)
                intent.putExtra("Sex",sex)
                intent.putExtra("Goal",goal)
                intent.putExtra("Weight",weight)
                intent.putExtra("Height",height)
                startActivity(intent)
                finish()
            }
        }

        back_button.setOnClickListener {
            val intent = Intent(this@GoalActivity,InviteCodeActivity::class.java)
            intent.putExtra("User_Type",user_type)
            intent.putExtra("Full_Name",full_name)
            intent.putExtra("Birthday",birthday)
            intent.putExtra("Address",address)
            intent.putExtra("Phone_Number",phone_number)
            intent.putExtra("Trainer_Code", trainer_code)
            startActivity(intent)
            finish()
        }
    }
}