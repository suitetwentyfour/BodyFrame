package com.suitetwentyfour.bodyframe.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.suitetwentyfour.bodyframe.R
import com.suitetwentyfour.bodyframe.controller.API_Controller
import com.suitetwentyfour.bodyframe.model.Profile
import com.mikhaellopez.circularimageview.CircularImageView
import com.suitetwentyfour.bodyframe.model.User
import android.app.Activity
import android.app.AlertDialog
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import android.provider.Contacts.SettingsColumns.KEY
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.amazonaws.auth.BasicAWSCredentials
// AWS Imports
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.s3.transferutility.*;
import java.io.File
import java.io.IOException
import com.amazonaws.services.s3.AmazonS3Client;
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.util.*

class ProfileActivity : AppCompatActivity() {

    val GALLERY = 1
    val CAMERA = 2
    var user_id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val test = API_Controller()
        val user = intent.extras?.get("user_object") as User
        var profile = test.Get_Profile(user.get_user_id())
        val image_button = findViewById<CircularImageView>(R.id.imageView_user)
        val update_button = findViewById<Button>(R.id.button_update)
        val back_button = findViewById<ImageButton>(R.id.button_back)
        val birthday_field = findViewById<EditText>(R.id.editText_birthday)
        val phone_field = findViewById<EditText>(R.id.editText_phone_number)

        user_id = user.get_user_id()

        fun set_values() {
            if(user.get_user_type() == "TRAINER")
            {
                findViewById<LinearLayout>(R.id.layout_sex).visibility = View.INVISIBLE
                findViewById<LinearLayout>(R.id.layout_goal).visibility = View.INVISIBLE
                findViewById<LinearLayout>(R.id.layout_weight).visibility = View.INVISIBLE
                findViewById<LinearLayout>(R.id.layout_height).visibility = View.INVISIBLE
            }
            val full_name = if(profile.get_first_name() != "" && profile.get_last_name() != "") profile.get_first_name() + " " + profile.get_last_name()
                            else "Name"
            val birth_day = if(profile.get_birth_day() != "") profile.get_birth_day() else "Birthday"
            val address = if(profile.get_address() != "") profile.get_address() else "Address"
            val phone_number = if(profile.get_phone_number() != "") profile.get_phone_number() else "Phone Number"
            val weight = if(profile.get_weight() != "") profile.get_weight() else "Weight"
            val height = if(profile.get_height() != "") profile.get_height() else "Height"
            findViewById<EditText>(R.id.editText_name).hint = full_name
            findViewById<EditText>(R.id.editText_birthday).hint = birth_day
            findViewById<EditText>(R.id.editText_address).hint = address
            findViewById<EditText>(R.id.editText_phone_number).hint = phone_number
            findViewById<Spinner>(R.id.spinner_sex).setSelection(if (profile.get_sex() == "M") 0 else 1)
            findViewById<Spinner>(R.id.spinner_goal).setSelection(if (profile.get_goal() == "Gain Muscles") 0 else 1)
            findViewById<EditText>(R.id.editText_weight).hint = weight
            findViewById<EditText>(R.id.editText_height).hint = height
        }

        set_values()

        fun choosePhotoFromGallary() {
            val galleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

            startActivityForResult(galleryIntent, GALLERY)
        }

        fun takePhotoFromCamera() {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA)
        }

        fun showPictureDialog() {
            val pictureDialog = AlertDialog.Builder(this)
            pictureDialog.setTitle("Select Action")
            val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
            pictureDialog.setItems(pictureDialogItems
            ) { dialog, which ->
                when (which) {
                    0 -> choosePhotoFromGallary()
                    1 -> takePhotoFromCamera()
                }
            }
            pictureDialog.show()
        }

        image_button.setOnClickListener { showPictureDialog() }

        update_button.setOnClickListener {
            val full_name = findViewById<EditText>(R.id.editText_name).text.toString()
            val birth_day = findViewById<EditText>(R.id.editText_birthday).text.toString()
            val address = findViewById<EditText>(R.id.editText_address).text.toString()
            val phone_number = findViewById<EditText>(R.id.editText_phone_number).text.toString()
            val sex = findViewById<Spinner>(R.id.spinner_sex).getSelectedItem().toString()
            val goal = findViewById<Spinner>(R.id.spinner_goal).getSelectedItem().toString()
            val weight = findViewById<EditText>(R.id.editText_weight).text.toString()
            val height = findViewById<EditText>(R.id.editText_height).text.toString()
            test.Update_Profile(user.get_user_id(), full_name, birth_day, address, phone_number,
                                sex, goal, weight, height)
            profile = test.Get_Profile(user.get_user_id())
            set_values()
            Toast.makeText(this@ProfileActivity, "Profile Updated!", Toast.LENGTH_SHORT).show()
        }

        back_button.setOnClickListener {
            val intent = Intent(this@ProfileActivity,MainActivity::class.java)
            intent.putExtra("user_object", user)
            startActivity(intent)
            finish()
        }

        birthday_field.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(birthday_field.text.length == 2 || birthday_field.text.length == 5)
                {
                    birthday_field.setText(birthday_field.text.toString() + "-")
                    birthday_field.setSelection((birthday_field.text.toString() + "-").length - 1)
                }
                if(birthday_field.text.length > 8)
                {
                    birthday_field.setText(birthday_field.text.toString().substring(0, birthday_field.text.toString().length - 1))
                    birthday_field.setSelection((birthday_field.text.toString() + "-").length - 1)
                }
            }
        })

        phone_field.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(phone_field.text.length == 3 || phone_field.text.length == 7)
                {
                    phone_field.setText(phone_field.text.toString() + "-")
                    phone_field.setSelection((phone_field.text.toString() + "-").length - 1)
                }
                if(phone_field.text.length > 12)
                {
                    phone_field.setText(phone_field.text.toString().substring(0, phone_field.text.toString().length - 1))
                    phone_field.setSelection((phone_field.text.toString() + "-").length - 1)
                }
            }
        })
    }

    public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY)
        {
            if (data != null)
            {
                val contentURI = data!!.data
                try
                {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    val path = saveImage(bitmap)
                    findViewById<CircularImageView>(R.id.imageView_user).setImageBitmap(bitmap)
                }
                catch (e: IOException) {
                    e.printStackTrace()
                }

            }

        }
        else if (requestCode == CAMERA)
        {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            findViewById<CircularImageView>(R.id.imageView_user).setImageBitmap(thumbnail)
            saveImage(thumbnail)
        }
    }

    fun saveImage(myBitmap: Bitmap):String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
            (Environment.getExternalStorageDirectory()).toString())
        // have the object build the directory structure, if needed.
        println("fee" + wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists())
        {

            wallpaperDirectory.mkdirs()
        }

        try
        {
            println("heel" + wallpaperDirectory.toString())
            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                .getTimeInMillis()).toString() + ".jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this,
                arrayOf(f.getPath()),
                arrayOf("image/jpeg"), null)
            fo.close()
            println("TAG" + "File Saved::--->" + f.getAbsolutePath())

            return f.getAbsolutePath()
        }
        catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }
}