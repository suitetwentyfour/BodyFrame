package com.suitetwentyfour.bodyframe.controller

import java.net.URL
import android.os.StrictMode
import com.suitetwentyfour.bodyframe.model.Client
import com.suitetwentyfour.bodyframe.model.Notification
import com.suitetwentyfour.bodyframe.model.Profile
import com.suitetwentyfour.bodyframe.model.User
import com.suitetwentyfour.bodyframe.model.Message
import com.suitetwentyfour.bodyframe.model.Activity
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection

class API_Controller
{
    private val WEB_ADDRESS = "http://bodyframe-test.d32yqpwedm.us-east-2.elasticbeanstalk.com/"

    // Working with activities
    fun Assign_Activity(assigner :String, assignee :String, activity_type :String, activity_name :String, activity_description :String,
                        media_link :String, memo :String, activity_time :String, calories :String): Boolean {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("assigner", "UTF-8") + "=" + URLEncoder.encode(assigner, "UTF-8")
            reqParam += "&" + URLEncoder.encode("assignee", "UTF-8") + "=" + URLEncoder.encode(assignee, "UTF-8")
            reqParam += "&" + URLEncoder.encode("activity_type", "UTF-8") + "=" + URLEncoder.encode(activity_type, "UTF-8")
            reqParam += "&" + URLEncoder.encode("activity_name", "UTF-8") + "=" + URLEncoder.encode(activity_name, "UTF-8")
            reqParam += "&" + URLEncoder.encode("activity_description", "UTF-8") + "=" + URLEncoder.encode(activity_description, "UTF-8")
            reqParam += "&" + URLEncoder.encode("media_link", "UTF-8") + "=" + URLEncoder.encode(media_link, "UTF-8")
            reqParam += "&" + URLEncoder.encode("memo", "UTF-8") + "=" + URLEncoder.encode(memo, "UTF-8")
            reqParam += "&" + URLEncoder.encode("activity_time", "UTF-8") + "=" + URLEncoder.encode(activity_time, "UTF-8")
            reqParam += "&" + URLEncoder.encode("calories", "UTF-8") + "=" + URLEncoder.encode(calories, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Assign_Activity")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    return true
                }
            }
        }
        catch (ex :Exception)
        {
            return false
        }
    }

    fun Get_Activities(assignee :String, activity_id :String): Array<Activity> {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("assignee", "UTF-8") + "=" + URLEncoder.encode(assignee, "UTF-8")
            reqParam += "&" + URLEncoder.encode("activity_id", "UTF-8") + "=" + URLEncoder.encode(activity_id, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Get_Activities")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    val jsonArray = JSONArray(response.toString())
                    val activities = mutableListOf<Activity>()
                    for (i in 0 until jsonArray.length()) {
                        val json = JSONArray(response.toString()).getJSONObject(i)
                        val activity_type = json.getString("activity_type")
                        val activity_name = json.getString("activity_name")
                        val activity_description = json.getString("activity_description")
                        val media_link = json.getString("media_link")
                        val assigner = json.getString("assigner")
                        val assignee = json.getString("assignee")
                        val memo = json.getString("memo")
                        val activity_time = json.getString("activity_time")
                        val calories = json.getString("calories")
                        val activity_id = json.getString("activity_id")
                        val completed = json.getString("completed")
                        val temp = Activity()
                        temp.set_activity_type(activity_type)
                        temp.set_activity_name(activity_name)
                        temp.set_activity_description(activity_description)
                        temp.set_media_link(media_link)
                        temp.set_assigner(assigner)
                        temp.set_assignee(assignee)
                        temp.set_memo(memo)
                        temp.set_activity_time(activity_time)
                        temp.set_calories(calories)
                        temp.set_activity_id(activity_id)
                        temp.set_completed(completed)
                        activities.add(temp)
                    }
                    return activities.toTypedArray()
                }
            }
        }
        catch (ex :Exception)
        {
            return arrayOf()
        }
    }

    fun Complete_Activity(assignee :String, activity_id :String): Boolean {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("assignee", "UTF-8") + "=" + URLEncoder.encode(assignee, "UTF-8")
            reqParam += "&" + URLEncoder.encode("activity_id", "UTF-8") + "=" + URLEncoder.encode(activity_id, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Complete_Activity")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    return true
                }
            }
        }
        catch (ex :Exception)
        {
            return false
        }
    }

    // Working with Messages
    fun Send_Message(user_id :String, send_to :String, message :String): Boolean {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8")
            reqParam += "&" + URLEncoder.encode("send_to", "UTF-8") + "=" + URLEncoder.encode(send_to, "UTF-8")
            reqParam += "&" + URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Send_Message")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    return true
                }
            }
        }
        catch (ex :Exception)
        {
            return false
        }
    }

    fun Get_Messages(user_id_one :String, messager :String): Array<Message> {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id_one, "UTF-8")
            reqParam += "&" + URLEncoder.encode("messager", "UTF-8") + "=" + URLEncoder.encode(messager, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Get_Messages")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    val jsonArray = JSONArray(response.toString())
                    val messages = mutableListOf<Message>()
                    for (i in 0 until jsonArray.length()) {
                        val json = JSONArray(response.toString()).getJSONObject(i)
                        val user_id = json.getString("user_id")
                        val message_from = json.getString("message_from")
                        val message_date = json.getString("message_date")
                        val message = json.getString("message")
                        val message_id = json.getString("message_id")
                        val read = json.getString("read")
                        val temp = Message()
                        temp.set_user_id(user_id)
                        temp.set_message_from(message_from)
                        temp.set_message_date(message_date)
                        temp.set_message(message)
                        temp.set_message_id(message_id)
                        temp.set_read(read)
                        temp.is_mine(user_id_one == message_from)
                        messages.add(temp)
                    }
                    return messages.toTypedArray()
                }
            }
        }
        catch (ex :Exception)
        {
            return arrayOf()
        }
    }

    fun Read_Message(user_id :String, message_id :String): Boolean {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8")
            reqParam += "&" + URLEncoder.encode("message_id", "UTF-8") + "=" + URLEncoder.encode(message_id, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Read_Message")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    return true
                }
            }
        }
        catch (ex :Exception)
        {
            return false
        }
    }

    // Working with Notifications
    fun Send_Notification(user_id :String, notification :String): Boolean {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8")
            reqParam += "&" + URLEncoder.encode("notification", "UTF-8") + "=" + URLEncoder.encode(notification, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Send_Notification")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    return true
                }
            }
        }
        catch (ex :Exception)
        {
            return false
        }
    }

    fun Get_Notifications(user_id :String): Array<Notification> {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Get_Notifications")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    val jsonArray = JSONArray(response.toString())
                    val notifications = mutableListOf<Notification>()
                    for (i in 0 until jsonArray.length()) {
                        val json = JSONArray(response.toString()).getJSONObject(i)
                        val notification_id = json.getString("notification_id")
                        val notification = json.getString("notification")
                        val notification_date = json.getString("notificaton_date")
                        val temp = Notification()
                        temp.set_notification_id(notification_id)
                        temp.set_notification(notification)
                        temp.set_notification_date(notification_date)
                        notifications.add(temp)
                    }
                    return notifications.toTypedArray()
                }
            }
        }
        catch (ex :Exception)
        {
            return arrayOf()
        }
    }

    fun Read_Notification(user_id :String, notification_id :String): Boolean {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8")
            reqParam += "&" + URLEncoder.encode("message_id", "UTF-8") + "=" + URLEncoder.encode(notification_id, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Read_Notification")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    return true
                }
            }
        }
        catch (ex :Exception)
        {
            return false
        }
    }

    // Working with Payment
    fun Pay_APP(): Boolean {
        return false
    }

    fun Pay_Trainer(): Boolean {
        return false
    }

    fun Get_Payment_History(): Boolean {
        return false
    }

    // Working with Support
    fun Send_Support_Ticket(): Boolean {
        return false
    }

    fun Get_Support_Ticket(): Boolean {
        return false
    }

    fun Update_Support_Ticket(): Boolean {
        return false
    }

    // Working with End User Actions
    fun Login(email :String, password :String): User {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam =
                URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")
            reqParam += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(
                password,
                "UTF-8"
            )
            val mURL = URL(WEB_ADDRESS + "Login")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()
                    println(response)

                    val json = JSONArray(response.toString()).getJSONObject(0)
                    val user_id = json.getString("user_id")
                    val user_type = json.getString("user_type")
                    val user_email = json.getString("email")
                    val assigned_to = json.getString("assigned_to")
                    val member_since = json.getString("member_since")
                    val paid_until = json.getString("paid_until")
                    val user = User()
                    user.set_user_id(user_id)
                    user.set_user_type(user_type)
                    user.set_user_email(user_email)
                    user.set_assigned_to(assigned_to)
                    user.set_member_since(member_since)
                    user.set_paid_until(paid_until)
                    return user
                }
            }
        }
        catch (ex :Exception)
        {
            println(ex.message)
            return User()
        }
    }

    fun Create_Trainer(email :String, password :String, user_type :String, full_name :String,
                       birth_day :String, address :String, phone_number :String, assigned_to :String): Boolean {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")
            reqParam += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
            reqParam += "&" + URLEncoder.encode("user_type", "UTF-8") + "=" + URLEncoder.encode(user_type, "UTF-8")
            reqParam += "&" + URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(full_name.split(" ")[0], "UTF-8")
            reqParam += "&" + URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(full_name.split(" ")[1], "UTF-8")
            reqParam += "&" + URLEncoder.encode("birth_day", "UTF-8") + "=" + URLEncoder.encode(birth_day, "UTF-8")
            reqParam += "&" + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8")
            reqParam += "&" + URLEncoder.encode("phone_number", "UTF-8") + "=" + URLEncoder.encode(phone_number, "UTF-8")
            reqParam += "&" + URLEncoder.encode("sex", "UTF-8") + "=" + URLEncoder.encode("T", "UTF-8")
            reqParam += "&" + URLEncoder.encode("goal", "UTF-8") + "=" + URLEncoder.encode("Trainer", "UTF-8")
            reqParam += "&" + URLEncoder.encode("weight", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")
            reqParam += "&" + URLEncoder.encode("height", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")
            reqParam += "&" + URLEncoder.encode("assigned_to", "UTF-8") + "=" + URLEncoder.encode(assigned_to, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Create_User")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    val respond = response.split(",")[0].split("{")[1].split(":")[1].replace(" ", "").replace("\"","")
                    println(respond)
                    if(respond == "200")return true else return false
                }
            }
        }
        catch (ex :Exception)
        {
            println(ex)
            return false
        }
    }

    fun Create_User(email :String, password :String, user_type :String, full_name :String,
                    birth_day :String, address :String, phone_number :String, sex :String,
                    goal :String, weight :String, height :String, assigned_to :String): Boolean {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")
                    reqParam += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
                    reqParam += "&" + URLEncoder.encode("user_type", "UTF-8") + "=" + URLEncoder.encode(user_type, "UTF-8")
                    reqParam += "&" + URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(full_name.split(" ")[0], "UTF-8")
                    reqParam += "&" + URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(full_name.split(" ")[1], "UTF-8")
                    reqParam += "&" + URLEncoder.encode("birth_day", "UTF-8") + "=" + URLEncoder.encode(birth_day, "UTF-8")
                    reqParam += "&" + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8")
                    reqParam += "&" + URLEncoder.encode("phone_number", "UTF-8") + "=" + URLEncoder.encode(phone_number, "UTF-8")
                    reqParam += "&" + URLEncoder.encode("sex", "UTF-8") + "=" + URLEncoder.encode(sex[0].toString(), "UTF-8")
                    reqParam += "&" + URLEncoder.encode("goal", "UTF-8") + "=" + URLEncoder.encode(goal, "UTF-8")
                    reqParam += "&" + URLEncoder.encode("weight", "UTF-8") + "=" + URLEncoder.encode(weight, "UTF-8")
                    reqParam += "&" + URLEncoder.encode("height", "UTF-8") + "=" + URLEncoder.encode(height, "UTF-8")
                    reqParam += "&" + URLEncoder.encode("assigned_to", "UTF-8") + "=" + URLEncoder.encode(assigned_to, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Create_User")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    val respond = response.split(",")[0].split("{")[1].split(":")[1].replace(" ", "").replace("\"","")
                    println(respond)
                    if(respond == "200")return true else return false
                }
            }
        }
        catch (ex :Exception)
        {
            println(ex)
            return false
        }
    }

    fun Update_User(user_id :String, password :String): Boolean {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8")
            reqParam += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Update_Profile")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    val json = JSONArray(response.toString()).getJSONObject(0)
                    println(json)
                    return true
                }
            }
        }
        catch (ex :Exception)
        {
            return false
        }
    }

    fun Get_Profile(user_id :String): Profile {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Get_Profile")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    val json = JSONArray(response.toString()).getJSONObject(0)
                    val first_name = json.getString("first_name")
                    val last_name = json.getString("last_name")
                    val birth_day = json.getString("birth_day")
                    val address = json.getString("address")
                    val phone_number = json.getString("phone_number")
                    val sex = json.getString("sex")
                    val goal = json.getString("goal")
                    val weight = json.getString("weight")
                    val height = json.getString("height")
                    val picture = json.getString("profile_picture")
                    val profile = Profile()
                    profile.set_first_name(first_name)
                    profile.set_last_name(last_name)
                    profile.set_birth_day(birth_day)
                    profile.set_address(address)
                    profile.set_phone_number(phone_number)
                    profile.set_sex(sex)
                    profile.set_goal(goal)
                    profile.set_weight(weight)
                    profile.set_height(height)
                    profile.set_profile_picture(picture)
                    return profile
                }
            }
        }
        catch (ex :Exception)
        {
            return Profile()
        }
    }

    fun Update_Profile(user_id :String, full_name :String, birth_day :String, address :String,
                       phone_number :String, sex :String, goal :String, weight :String,
                       height :String): Boolean {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8")
            reqParam += "&" + URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(full_name.split(" ")[0], "UTF-8")
            reqParam += "&" + URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(full_name.split(" ")[1], "UTF-8")
            reqParam += "&" + URLEncoder.encode("birth_day", "UTF-8") + "=" + URLEncoder.encode(birth_day, "UTF-8")
            reqParam += "&" + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8")
            reqParam += "&" + URLEncoder.encode("phone_number", "UTF-8") + "=" + URLEncoder.encode(phone_number, "UTF-8")
            reqParam += "&" + URLEncoder.encode("sex", "UTF-8") + "=" + URLEncoder.encode(sex[0].toString(), "UTF-8")
            reqParam += "&" + URLEncoder.encode("goal", "UTF-8") + "=" + URLEncoder.encode(goal, "UTF-8")
            reqParam += "&" + URLEncoder.encode("weight", "UTF-8") + "=" + URLEncoder.encode(weight, "UTF-8")
            reqParam += "&" + URLEncoder.encode("height", "UTF-8") + "=" + URLEncoder.encode(height, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Update_Profile")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    val json = JSONArray(response.toString()).getJSONObject(0)
                    println(json)
                    return true
                }
            }
        }
        catch (ex :Exception)
        {
            return false
        }
    }

    fun Set_Card_Information(user_id :String, full_name :String, card_number :String, expiration :String,
                             cvv :String): Boolean {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8")
            reqParam += "&" + URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(full_name.split(" ")[0], "UTF-8")
            reqParam += "&" + URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(full_name.split(" ")[1], "UTF-8")
            reqParam += "&" + URLEncoder.encode("card_number", "UTF-8") + "=" + URLEncoder.encode(card_number, "UTF-8")
            reqParam += "&" + URLEncoder.encode("expiration", "UTF-8") + "=" + URLEncoder.encode(expiration, "UTF-8")
            reqParam += "&" + URLEncoder.encode("cvv", "UTF-8") + "=" + URLEncoder.encode(cvv, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Set_Card_Information")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    return true
                }
            }
        }
        catch (ex :Exception)
        {
            return false
        }
    }

    fun Get_Card_Information(user_id :String): Boolean {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Get_Card_Information")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    val json = JSONArray(response.toString()).getJSONObject(0)
                    val first_name = json.getString("first_name")
                    val last_name = json.getString("last_name")
                    val birth_day = json.getString("birth_day")
                    val address = json.getString("address")
                    val phone_number = json.getString("phone_number")
                    val sex = json.getString("sex")
                    val goal = json.getString("goal")
                    val weight = json.getString("weight")
                    val height = json.getString("height")
                    val profile = Profile()
                    profile.set_first_name(first_name)
                    profile.set_last_name(last_name)
                    profile.set_birth_day(birth_day)
                    profile.set_address(address)
                    profile.set_phone_number(phone_number)
                    profile.set_sex(sex)
                    profile.set_goal(goal)
                    profile.set_weight(weight)
                    profile.set_height(height)
                    return true
                }
            }
        }
        catch (ex :Exception)
        {
            return false
        }
    }

    fun Update_Card_Information(user_id :String, full_name :String, card_number :String, expiration :String,
                                cvv :String): Boolean {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8")
            reqParam += "&" + URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(full_name.split(" ")[0], "UTF-8")
            reqParam += "&" + URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(full_name.split(" ")[1], "UTF-8")
            reqParam += "&" + URLEncoder.encode("card_number", "UTF-8") + "=" + URLEncoder.encode(card_number, "UTF-8")
            reqParam += "&" + URLEncoder.encode("expiration", "UTF-8") + "=" + URLEncoder.encode(expiration, "UTF-8")
            reqParam += "&" + URLEncoder.encode("cvv", "UTF-8") + "=" + URLEncoder.encode(cvv, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Update_Card_Information")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    return true
                }
            }
        }
        catch (ex :Exception)
        {
            return false
        }
    }

    fun Forgot_Password(email: String): Boolean {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Forgot_Password")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    return true
                }
            }
        }
        catch (ex :Exception)
        {
            return false
        }
    }

    fun Get_Contact_List(user_id: String): Array<Client> {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var reqParam = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8")
            val mURL = URL(WEB_ADDRESS + "Get_Contact_List")

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream())
                wr.write(reqParam)
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()

                    println(response)
                    val jsonArray = JSONArray(response.toString())
                    val client = mutableListOf<Client>()
                    for (i in 0 until jsonArray.length()) {
                        val json = JSONArray(response.toString()).getJSONObject(i)
                        val user_id = json.getString("user_id")
                        val first_name = json.getString("first_name")
                        val last_name = json.getString("last_name")
                        val temp = Client()
                        temp.set_user_id(user_id)
                        temp.set_first_name(first_name)
                        temp.set_last_name(last_name)
                        client.add(temp)
                    }
                    return client.toTypedArray()
                }
            }
        }
        catch (ex :Exception)
        {
            return arrayOf()
        }
    }
}