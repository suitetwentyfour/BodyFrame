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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
// AWS Imports
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.s3.transferutility.*;
import com.amazonaws.services.s3.AmazonS3Client
import com.suitetwentyfour.bodyframe.controller.Activity_Adaptor
import com.suitetwentyfour.bodyframe.controller.FAQ_Adaptor
import java.io.File

class FAQActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        val user = intent.extras?.get("user_object") as User
        val back_button = findViewById<ImageButton>(R.id.button_back)

        val adaptor = FAQ_Adaptor(getResources().getStringArray(R.array.faq_recycler_view), this@FAQActivity)
        findViewById<RecyclerView>(R.id.recyclerView_faq).layoutManager = LinearLayoutManager(this@FAQActivity)
        findViewById<RecyclerView>(R.id.recyclerView_faq).adapter = adaptor

        back_button.setOnClickListener {
            val intent = Intent(this@FAQActivity,SettingsActivity::class.java)
            intent.putExtra("user_object", user)
            startActivity(intent)
            finish()
        }
    }
}