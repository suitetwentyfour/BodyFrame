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
import com.suitetwentyfour.bodyframe.controller.API_Controller

class CredentialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credential)

        val user_type = intent.getStringExtra("User_Type")
        val full_name = intent.getStringExtra("Full_Name")
        val birthday = intent.getStringExtra("Birthday")
        val address = intent.getStringExtra("Address")
        val phone_number = intent.getStringExtra("Phone_Number")
        val login_button = findViewById<TextView>(R.id.textView_login)
        val continue_button = findViewById<Button>(R.id.button_payment_button)
        val back_button = findViewById<Button>(R.id.button_back)

        login_button.setOnClickListener {
            val intent = Intent(this@CredentialActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        continue_button.setOnClickListener {
            var username = findViewById<EditText>(R.id.editText_Username).text.toString()
            var password = findViewById<EditText>(R.id.editText_Password).text.toString()
            if(username == "" || password == "")
            {
                if(username == "") { Toast.makeText(this@CredentialActivity, "Username cannot be blank.", Toast.LENGTH_SHORT).show() }
                else if(password == "") { Toast.makeText(this@CredentialActivity, "Password cannot be blank.", Toast.LENGTH_SHORT).show() }
            }
            else
            {
                if(user_type == "TRAINER")
                {
                    var test: API_Controller = API_Controller()
                    var trainer_code :String
                    try{ trainer_code = intent.getStringExtra("Trainer_Code") }
                    catch(ex :Exception){ trainer_code = "" }
                    var create = test.Create_Trainer(username, password, user_type, full_name, birthday, address, phone_number, trainer_code)
                    if(create)
                    {
                        //val intent = Intent(this@CredentialActivity, PaymentActivity::class.java)
                        val intent = Intent(this@CredentialActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this@CredentialActivity, "Username is taken.", Toast.LENGTH_SHORT).show()
                    }
                }
                else if(user_type == "USER")
                {
                    val trainer_code = intent.getStringExtra("Trainer_Code")
                    val sex = intent.getStringExtra("Sex")
                    val goal = intent.getStringExtra("Goal")
                    val weight = intent.getStringExtra("Weight")
                    val height = intent.getStringExtra("Height")
                    var test: API_Controller = API_Controller()
                    var create = test.Create_User(username, password, user_type, full_name, birthday, address, phone_number, sex, goal, weight, height, trainer_code)
                    if(create)
                    {
                        val intent = Intent(this@CredentialActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this@CredentialActivity, "Username is taken.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        back_button.setOnClickListener {
            if(user_type == "TRAINER")
            {
                val intent = Intent(this@CredentialActivity, SignUpActivity::class.java)
                intent.putExtra("User_Type",user_type)
                startActivity(intent)
                finish()
            }
            else if(user_type == "USER")
            {
                val intent = Intent(this@CredentialActivity, GoalActivity::class.java)
                intent.putExtra("User_Type",user_type)
                intent.putExtra("Full_Name",full_name)
                intent.putExtra("Birthday",birthday)
                intent.putExtra("Address",address)
                intent.putExtra("Phone_Number",phone_number)
                intent.putExtra("Trainer_Code",intent.getStringExtra("Trainer_Code"))
                intent.putExtra("Sex",intent.getStringExtra("Sex"))
                intent.putExtra("Goal",intent.getStringExtra("Goal"))
                intent.putExtra("Weight",intent.getStringExtra("Weight"))
                intent.putExtra("Height",intent.getStringExtra("Height"))
                startActivity(intent)
                finish()
            }
        }
    }
}