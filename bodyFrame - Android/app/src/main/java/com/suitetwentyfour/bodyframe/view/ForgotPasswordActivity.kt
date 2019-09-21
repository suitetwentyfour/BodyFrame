package com.suitetwentyfour.bodyframe.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.suitetwentyfour.bodyframe.R
import com.suitetwentyfour.bodyframe.controller.API_Controller
import kotlinx.android.synthetic.main.activity_login.*

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val reset_button = findViewById<Button>(R.id.button_reset_password)
        val back_button = findViewById<Button>(R.id.button_back)

        reset_button.setOnClickListener {
            var test = API_Controller()
            test.Forgot_Password(findViewById<EditText>(R.id.editText_Username).text.toString())
            Toast.makeText(this@ForgotPasswordActivity, "Check your registered email.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@ForgotPasswordActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        back_button.setOnClickListener {
            val intent = Intent(this@ForgotPasswordActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}