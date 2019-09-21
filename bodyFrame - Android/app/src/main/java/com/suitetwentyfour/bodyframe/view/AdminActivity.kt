package com.suitetwentyfour.bodyframe.view

import androidx.fragment.app.Fragment
import android.R
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.suitetwentyfour.bodyframe.controller.API_Controller
import kotlinx.android.synthetic.main.activity_login.*


class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.suitetwentyfour.bodyframe.R.layout.activity_admin)

        val logout_button = findViewById<Button>(com.suitetwentyfour.bodyframe.R.id.button_logout)

        logout_button.setOnClickListener {
            val intent = Intent(this@AdminActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}