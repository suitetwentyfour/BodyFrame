package com.suitetwentyfour.bodyframe.view

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikhaellopez.circularimageview.CircularImageView
import com.suitetwentyfour.bodyframe.R
import com.suitetwentyfour.bodyframe.controller.API_Controller
import com.suitetwentyfour.bodyframe.model.Client
import com.suitetwentyfour.bodyframe.model.User
import java.io.IOException

class AssignActivity : AppCompatActivity() {

    val REQUEST_IMAGE_GET = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign)

        val test = API_Controller()
        val user = intent.extras?.get("user_object") as User
        val client = intent.extras?.get("client_object") as Client
        val primary_button = findViewById<Button>(R.id.button_primary)
        val secondary_button = findViewById<Button>(R.id.button_secondary)
        val upload_button = findViewById<Button>(R.id.button_upload)
        val upload_button_image = findViewById<ImageButton>(R.id.imageButton_upload)
        val add_button = findViewById<TextView>(R.id.textView_add_task)
        val back_button = findViewById<ImageButton>(R.id.button_back)

        var type = "Workout"
        var link = ""

        findViewById<EditText>(R.id.editText_task_type).hint = "Biceps"
        findViewById<EditText>(R.id.editText_task_name).hint = "Workout Name"

        primary_button.setOnClickListener {
            type = "WORKOUT"
            findViewById<EditText>(R.id.editText_task_type).hint = "Biceps"
            findViewById<EditText>(R.id.editText_task_name).hint = "Workout Name"
            Toast.makeText(this@AssignActivity, "Switched to workout!", Toast.LENGTH_SHORT).show()
        }

        secondary_button.setOnClickListener {
            type = "MEAL"
            findViewById<EditText>(R.id.editText_task_type).hint = "Lunch"
            findViewById<EditText>(R.id.editText_task_name).hint = "Meal Name"
            Toast.makeText(this@AssignActivity, "Switched to meal!", Toast.LENGTH_SHORT).show()
        }

        upload_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_IMAGE_GET)
        }

        upload_button_image.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_IMAGE_GET)
        }

        add_button.setOnClickListener {
            val assigner = user.get_user_id()
            val assignee = client.get_user_id()
            val activity_type = type
            val activity_name = findViewById<EditText>(R.id.editText_task_name).text.toString()
            val activity_description = findViewById<EditText>(R.id.editText_task_type).text.toString()
            val media_link = link
            val memo = findViewById<EditText>(R.id.editText_description).text.toString()
            var activity_time = findViewById<EditText>(R.id.editText_time).text.toString()
            var calories = findViewById<EditText>(R.id.editText_calories).text.toString()

            if(activity_time == "") { activity_time = "0" }
            if(calories == "") { calories = "0" }

            if(activity_name == "" || activity_description == "")
            {
                if(activity_name == "") { Toast.makeText(this@AssignActivity,"Activity type cannot be blank.", Toast.LENGTH_SHORT).show() }
                else if(activity_description == "") { Toast.makeText(this@AssignActivity, "Actvity name cannot be blank.", Toast.LENGTH_SHORT).show() }
            }
            else
            {
                test.Assign_Activity(assigner, assignee, activity_type, activity_name, activity_description, media_link, memo, activity_time, calories)
                Toast.makeText(this@AssignActivity, "Task was added!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@AssignActivity,ClientActivity::class.java)
                intent.putExtra("user_object", user)
                intent.putExtra("client_object", client)
                startActivity(intent)
                finish()
            }
        }

        back_button.setOnClickListener {
            val intent = Intent(this@AssignActivity,ClientActivity::class.java)
            intent.putExtra("user_object", user)
            intent.putExtra("client_object", client)
            startActivity(intent)
            finish()
        }
    }

    public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_GET)
        {
            if (data != null)
            {
                val contentURI = data!!.data
                try
                {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    //val path = saveImage(bitmap)
                    Toast.makeText(this@AssignActivity, "Image Loaded!", Toast.LENGTH_SHORT).show()
                    findViewById<ImageButton>(R.id.imageButton_upload)!!.setImageBitmap(bitmap)
                    findViewById<Button>(R.id.button_upload).visibility = View.GONE
                    findViewById<ImageButton>(R.id.imageButton_upload).visibility = View.VISIBLE

                }
                catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this@AssignActivity, "Failed!", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}