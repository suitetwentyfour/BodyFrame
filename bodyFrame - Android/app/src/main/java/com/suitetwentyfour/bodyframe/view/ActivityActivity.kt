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
import android.view.View
import android.widget.*
// AWS Imports
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.s3.transferutility.*;
import com.amazonaws.services.s3.AmazonS3Client
import com.suitetwentyfour.bodyframe.model.Client
import java.io.File

class ActivityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity)

        val test = API_Controller()
        val user = intent.extras?.get("user_object") as User
        val activity = intent.extras?.get("activity_object") as Activity
        val done_button = findViewById<Button>(R.id.button_done)
        val back_button = findViewById<ImageButton>(R.id.button_back)

        findViewById<TextView>(R.id.textView_activity_brief).text = activity.get_activity_name()
        findViewById<TextView>(R.id.textView_activity_time_calorie).text = activity.get_activity_time() + " minutes," + activity.get_calories() + " cal"
        findViewById<TextView>(R.id.textView_activity_time_description).text = activity.get_memo()
        if(activity.get_memo() != ""){ findViewById<TextView>(R.id.textView_activity_time_description).visibility = View.VISIBLE }

        done_button.setOnClickListener {
            test.Complete_Activity(activity.get_assignee(), activity.get_activity_id())
            var name = test.Get_Profile(user.get_user_id()).get_first_name() + " " + test.Get_Profile(user.get_user_id()).get_last_name()
            test.Send_Notification(user.get_assigned_to(),name + ";Has completed the " + activity.activity_description + " task")
            Toast.makeText(this@ActivityActivity, "Congratulations!\nYour trainer has been notified of your completion!", Toast.LENGTH_SHORT).show()
        }

        back_button.setOnClickListener {
            try
            {
                val client = intent.extras?.get("client_object") as Client
                val intent = Intent(this@ActivityActivity, ClientActivity::class.java)
                intent.putExtra("user_object", user)
                intent.putExtra("client_object", client)
                startActivity(intent)
                finish()
            }
            catch(ex :Exception)
            {
                val intent = Intent(this@ActivityActivity,MainActivity::class.java)
                intent.putExtra("user_object", user)
                startActivity(intent)
                finish()
            }
        }
    }
}