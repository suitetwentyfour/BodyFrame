package com.suitetwentyfour.bodyframe.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.suitetwentyfour.bodyframe.R
import com.suitetwentyfour.bodyframe.controller.API_Controller
import com.suitetwentyfour.bodyframe.model.User
import kotlinx.android.synthetic.main.activity_login.*
import java.io.Serializable


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login_button = findViewById<Button>(R.id.button_Login)
        val register_button = findViewById<TextView>(R.id.textView_Register)
        val signup_button = findViewById<TextView>(R.id.textView_Sign_Up)
        val forgot_button = findViewById<TextView>(R.id.textView_Forgot_Password)

        login_button.setOnClickListener {
            var username: String = findViewById<EditText>(R.id.editText_Username).text.toString()
            var password: String = findViewById<EditText>(com.suitetwentyfour.bodyframe.R.id.editText_Password).text.toString()

            if(username == "" || password == "")
            {
                if(username == "")
                { Toast.makeText(this@LoginActivity, "Username cannot be blank.", Toast.LENGTH_SHORT).show() }
                else if(password == "")
                { Toast.makeText(this@LoginActivity, "Password cannot be blank.", Toast.LENGTH_SHORT).show() }
            }
            else
            {
                val test = API_Controller()
                val user = test.Login(username.trim(), password)
                if(user.get_user_id() != "")
                {
                    if(user.get_user_type() == "ADMIN")
                    {
                        val intent = Intent(this@LoginActivity, AdminActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else
                    {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.putExtra("user_object", user)
                        startActivity(intent)
                        finish()
                    }
                }
                else { Toast.makeText(this@LoginActivity, "Incorrect Username/Password.", Toast.LENGTH_SHORT).show() }
            }
        }

        register_button.setOnClickListener {
            val intent = Intent(this@LoginActivity,SignUpActivity::class.java)
            intent.putExtra("User_Type","TRAINER")
            startActivity(intent)
            finish()
        }

        signup_button.setOnClickListener {
            val intent = Intent(this@LoginActivity,SignUpActivity::class.java)
            intent.putExtra("User_Type","USER")
            startActivity(intent)
            finish()
        }

        forgot_button.setOnClickListener {
            val intent = Intent(this@LoginActivity,ForgotPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
