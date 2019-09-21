package com.suitetwentyfour.bodyframe.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.suitetwentyfour.bodyframe.R
import com.suitetwentyfour.bodyframe.controller.API_Controller
import com.suitetwentyfour.bodyframe.model.Profile
import com.mikhaellopez.circularimageview.CircularImageView
import com.suitetwentyfour.bodyframe.model.User
import com.suitetwentyfour.bodyframe.model.Activity
import android.graphics.Bitmap
import android.net.Uri
import android.widget.*
// AWS Imports
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.s3.transferutility.*;
import com.amazonaws.services.s3.AmazonS3Client
import java.io.File

class SupportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support)

        val user = intent.extras?.get("user_object") as User
        val support_button =  findViewById<TextView>(R.id.textView_email)
        val back_button = findViewById<ImageButton>(R.id.button_back)

        support_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, "support@bodyframe.app")
                putExtra(Intent.EXTRA_SUBJECT, "Body Frame Support")
            }
            startActivity(intent)
        }

        back_button.setOnClickListener {
            val intent = Intent(this@SupportActivity,SettingsActivity::class.java)
            intent.putExtra("user_object", user)
            startActivity(intent)
            finish()
        }
    }
}