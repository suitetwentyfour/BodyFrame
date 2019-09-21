package com.suitetwentyfour.bodyframe.view

import android.content.Intent
import android.net.wifi.hotspot2.pps.Credential
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.mikhaellopez.circularimageview.CircularImageView
import com.suitetwentyfour.bodyframe.R
import java.io.IOException
import android.app.AlertDialog
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import android.os.Environment
import android.media.MediaScannerConnection
import java.util.*

class SignUpActivity : AppCompatActivity() {

    val GALLERY = 1
    val CAMERA = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val user_type = intent.getStringExtra("User_Type")
        var name = ""
        var birth = ""
        var address = ""
        var phone = ""
        val login_button = findViewById<TextView>(R.id.textView_login)
        val continue_button = findViewById<Button>(R.id.button_continue)
        val invite_button = findViewById<Button>(R.id.button_trainer_invite)
        val birthday_field = findViewById<EditText>(R.id.editText_birthday)
        val phone_field = findViewById<EditText>(R.id.editText_phone_number)
        val profile_pic = findViewById<ImageView>(R.id.imageView_profile)

        if(user_type == "TRAINER")
        {
            findViewById<Button>(R.id.button_trainer_invite).visibility = View.VISIBLE
        }
        else { findViewById<Button>(R.id.button_trainer_invite).visibility = View.INVISIBLE }

        fun get_values(){
            name = findViewById<EditText>(R.id.editText_name).text.toString()
            birth = findViewById<EditText>(R.id.editText_birthday).text.toString()
            address = findViewById<EditText>(R.id.editText_address).text.toString()
            phone = findViewById<EditText>(R.id.editText_phone_number).text.toString()
        }

        login_button.setOnClickListener {
            val intent = Intent(this@SignUpActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        continue_button.setOnClickListener {
            get_values()
            if(name == "" || birth == "" || address == "" || phone == "")
            {
                if(name == "")
                { Toast.makeText(this@SignUpActivity, "Name cannot be blank.", Toast.LENGTH_SHORT).show() }
                else if(birth == "")
                { Toast.makeText(this@SignUpActivity, "Birthdate cannot be blank.", Toast.LENGTH_SHORT).show() }
                else if(address == "")
                { Toast.makeText(this@SignUpActivity, "Address cannot be blank.", Toast.LENGTH_SHORT).show() }
                else if(phone == "")
                { Toast.makeText(this@SignUpActivity, "Phone Number cannot be blank.", Toast.LENGTH_SHORT).show() }
            }
            else
            {
                if(user_type == "TRAINER")
                {
                    val intent = Intent(this@SignUpActivity,CredentialActivity::class.java)
                    intent.putExtra("User_Type",user_type)
                    intent.putExtra("Full_Name",name)
                    intent.putExtra("Birthday",birth)
                    intent.putExtra("Address",address)
                    intent.putExtra("Phone_Number",phone)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    val intent = Intent(this@SignUpActivity,InviteCodeActivity::class.java)
                    intent.putExtra("User_Type",user_type)
                    intent.putExtra("Full_Name",name)
                    intent.putExtra("Birthday",birth)
                    intent.putExtra("Address",address)
                    intent.putExtra("Phone_Number",phone)
                    startActivity(intent)
                    finish()
                }
            }
        }

        invite_button.setOnClickListener {
            get_values()
            if(name == "" || birth == "" || address == "" || phone == "")
            {
                if(name == "")
                { Toast.makeText(this@SignUpActivity, "Name cannot be blank.", Toast.LENGTH_SHORT).show() }
                else if(birth == "")
                { Toast.makeText(this@SignUpActivity, "Birthdate cannot be blank.", Toast.LENGTH_SHORT).show() }
                else if(address == "")
                { Toast.makeText(this@SignUpActivity, "Address cannot be blank.", Toast.LENGTH_SHORT).show() }
                else if(phone == "")
                { Toast.makeText(this@SignUpActivity, "Phone Number cannot be blank.", Toast.LENGTH_SHORT).show() }
            }
            else
            {
                val intent = Intent(this@SignUpActivity,InviteCodeActivity::class.java)
                intent.putExtra("User_Type",user_type)
                intent.putExtra("Full_Name",name)
                intent.putExtra("Birthday",birth)
                intent.putExtra("Address",address)
                intent.putExtra("Phone_Number",phone)
                startActivity(intent)
                finish()
            }
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

        profile_pic.setOnClickListener { showPictureDialog() }
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

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, GALLERY)
    }

    fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
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
                    findViewById<CircularImageView>(R.id.imageView_user).visibility = View.VISIBLE
                    findViewById<ImageView>(R.id.imageView_profile).visibility = View.GONE
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
            findViewById<CircularImageView>(R.id.imageView_user).visibility = View.VISIBLE
            findViewById<ImageView>(R.id.imageView_profile).visibility = View.GONE
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