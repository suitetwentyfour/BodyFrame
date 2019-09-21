package com.suitetwentyfour.bodyframe.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suitetwentyfour.bodyframe.R
import com.suitetwentyfour.bodyframe.controller.API_Controller
import com.suitetwentyfour.bodyframe.controller.Activity_Adaptor
import com.suitetwentyfour.bodyframe.controller.Client_Adaptor
import com.suitetwentyfour.bodyframe.controller.Notification_Adaptor
import com.suitetwentyfour.bodyframe.model.Activity
import com.suitetwentyfour.bodyframe.model.Client
import com.suitetwentyfour.bodyframe.model.User
import java.util.*

class ClientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

        val test = API_Controller()
        val user = intent.extras?.get("user_object") as User
        val client = intent.extras?.get("client_object") as Client
        val client_profile = test.Get_Profile(client.get_user_id())

        val primary_button = findViewById<Button>(R.id.button_primary)
        val secondary_button = findViewById<Button>(R.id.button_secondary)
        val assign_button = findViewById<Button>(R.id.button_assign_task)
        val back_button = findViewById<ImageButton>(R.id.button_back)
        val message_button = findViewById<TextView>(R.id.textView_send_message)
        // Calendar Buttons
        var expand_toggle = 0
        val expand_button = findViewById<ImageButton>(R.id.imageButton_expand)
        val button_cal_1_1 = findViewById<Button>(R.id.button_cal_1_1)
        val button_cal_1_2 = findViewById<Button>(R.id.button_cal_1_2)
        val button_cal_1_3 = findViewById<Button>(R.id.button_cal_1_3)
        val button_cal_1_4 = findViewById<Button>(R.id.button_cal_1_4)
        val button_cal_1_5 = findViewById<Button>(R.id.button_cal_1_5)
        val button_cal_1_6 = findViewById<Button>(R.id.button_cal_1_6)
        val button_cal_1_7 = findViewById<Button>(R.id.button_cal_1_7)

        fun set_calendar()
        {
            val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            val date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            when (day)
            {
                Calendar.MONDAY -> {
                    findViewById<Button>(R.id.button_cal_1_1).setBackground(this@ClientActivity.getDrawable(R.drawable.calandar_expand_icon))
                    findViewById<Button>(R.id.button_cal_1_1).text = date.toString()
                    findViewById<Button>(R.id.button_cal_1_2).text = (date + 1).toString()
                    findViewById<Button>(R.id.button_cal_1_3).text = (date + 2).toString()
                    findViewById<Button>(R.id.button_cal_1_4).text = (date + 3).toString()
                    findViewById<Button>(R.id.button_cal_1_5).text = (date + 4).toString()
                    findViewById<Button>(R.id.button_cal_1_6).text = (date + 5).toString()
                    findViewById<Button>(R.id.button_cal_1_7).text = (date + 6).toString()
                }
                Calendar.TUESDAY -> {
                    findViewById<Button>(R.id.button_cal_1_2).setBackground(this@ClientActivity.getDrawable(R.drawable.calandar_expand_icon))
                    findViewById<Button>(R.id.button_cal_1_1).text = (date - 1).toString()
                    findViewById<Button>(R.id.button_cal_1_2).text = date.toString()
                    findViewById<Button>(R.id.button_cal_1_3).text = (date + 1).toString()
                    findViewById<Button>(R.id.button_cal_1_4).text = (date + 2).toString()
                    findViewById<Button>(R.id.button_cal_1_5).text = (date + 3).toString()
                    findViewById<Button>(R.id.button_cal_1_6).text = (date + 4).toString()
                    findViewById<Button>(R.id.button_cal_1_7).text = (date + 5).toString()
                }
                Calendar.WEDNESDAY -> {
                    findViewById<Button>(R.id.button_cal_1_3).setBackground(this@ClientActivity.getDrawable(R.drawable.calandar_expand_icon))
                    findViewById<Button>(R.id.button_cal_1_1).text = (date - 2).toString()
                    findViewById<Button>(R.id.button_cal_1_2).text = (date - 1).toString()
                    findViewById<Button>(R.id.button_cal_1_3).text = date.toString()
                    findViewById<Button>(R.id.button_cal_1_4).text = (date + 1).toString()
                    findViewById<Button>(R.id.button_cal_1_5).text = (date + 2).toString()
                    findViewById<Button>(R.id.button_cal_1_6).text = (date + 3).toString()
                    findViewById<Button>(R.id.button_cal_1_7).text = (date + 4).toString()
                }
                Calendar.THURSDAY -> {
                    findViewById<Button>(R.id.button_cal_1_4).setBackground(this@ClientActivity.getDrawable(R.drawable.calandar_expand_icon))
                    findViewById<Button>(R.id.button_cal_1_1).text = (date - 3).toString()
                    findViewById<Button>(R.id.button_cal_1_2).text = (date - 2).toString()
                    findViewById<Button>(R.id.button_cal_1_3).text = (date - 1).toString()
                    findViewById<Button>(R.id.button_cal_1_4).text =  date.toString()
                    findViewById<Button>(R.id.button_cal_1_5).text = (date + 1).toString()
                    findViewById<Button>(R.id.button_cal_1_6).text = (date + 2).toString()
                    findViewById<Button>(R.id.button_cal_1_7).text = (date + 3).toString()
                }
                Calendar.FRIDAY -> {
                    findViewById<Button>(R.id.button_cal_1_5).setBackground(this@ClientActivity.getDrawable(R.drawable.calandar_expand_icon))
                    findViewById<Button>(R.id.button_cal_1_1).text = (date - 4).toString()
                    findViewById<Button>(R.id.button_cal_1_2).text = (date - 3).toString()
                    findViewById<Button>(R.id.button_cal_1_3).text = (date - 2).toString()
                    findViewById<Button>(R.id.button_cal_1_4).text =  (date - 1).toString()
                    findViewById<Button>(R.id.button_cal_1_5).text = date.toString()
                    findViewById<Button>(R.id.button_cal_1_6).text = (date + 1).toString()
                    findViewById<Button>(R.id.button_cal_1_7).text = (date + 2).toString()
                }
                Calendar.SATURDAY -> {
                    findViewById<Button>(R.id.button_cal_1_6).setBackground(this@ClientActivity.getDrawable(R.drawable.calandar_expand_icon))
                    findViewById<Button>(R.id.button_cal_1_1).text = (date - 5).toString()
                    findViewById<Button>(R.id.button_cal_1_2).text = (date - 4).toString()
                    findViewById<Button>(R.id.button_cal_1_3).text = (date - 3).toString()
                    findViewById<Button>(R.id.button_cal_1_4).text =  (date - 2).toString()
                    findViewById<Button>(R.id.button_cal_1_5).text = (date - 1).toString()
                    findViewById<Button>(R.id.button_cal_1_6).text = date.toString()
                    findViewById<Button>(R.id.button_cal_1_7).text = (date + 1).toString()
                }
                Calendar.SUNDAY -> {
                    findViewById<Button>(R.id.button_cal_1_7).setBackground(this@ClientActivity.getDrawable(R.drawable.calandar_expand_icon))
                    findViewById<Button>(R.id.button_cal_1_1).text = (date - 6).toString()
                    findViewById<Button>(R.id.button_cal_1_2).text = (date - 5).toString()
                    findViewById<Button>(R.id.button_cal_1_3).text = (date - 4).toString()
                    findViewById<Button>(R.id.button_cal_1_4).text =  (date - 3).toString()
                    findViewById<Button>(R.id.button_cal_1_5).text = (date - 2).toString()
                    findViewById<Button>(R.id.button_cal_1_6).text = (date - 1).toString()
                    findViewById<Button>(R.id.button_cal_1_7).text = date.toString()
                }
            }
        }

        fun set_expanded_calendar()
        {
            var day = 0
            val date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
            {
                Calendar.MONDAY -> { day = 1 }
                Calendar.TUESDAY -> { day = 2 }
                Calendar.WEDNESDAY -> { day = 3 }
                Calendar.THURSDAY -> { day = 4 }
                Calendar.FRIDAY -> { day = 5 }
                Calendar.SATURDAY -> { day = 6 }
                Calendar.SUNDAY -> { day = 7 }
            }
            var week = Calendar.getInstance().get(Calendar.WEEK_OF_MONTH)
            var element_name = "button_cal_" + week.toString() + "_" + day.toString()
            var temp = findViewById<Button>(getResources().getIdentifier(element_name, "id", getPackageName()))
            temp.setBackground(this@ClientActivity.getDrawable(R.drawable.calandar_expand_icon))
            temp.text = date.toString()

            var clear = "button_cal_1_" + day.toString()
            var cleared = findViewById<Button>(getResources().getIdentifier(clear, "id", getPackageName()))
            cleared.setBackgroundResource(0)

            findViewById<Button>(R.id.button_cal_1_1).text = "26"
            findViewById<Button>(R.id.button_cal_1_2).text = "27"
            findViewById<Button>(R.id.button_cal_1_3).text = "28"
            findViewById<Button>(R.id.button_cal_1_4).text = "29"
            findViewById<Button>(R.id.button_cal_1_5).text = "30"
            findViewById<Button>(R.id.button_cal_1_6).text = "31"
            findViewById<Button>(R.id.button_cal_1_7).text = (date - 14).toString()
            findViewById<Button>(R.id.button_cal_2_1).text = (date - 13).toString()
            findViewById<Button>(R.id.button_cal_2_2).text = (date - 12).toString()
            findViewById<Button>(R.id.button_cal_2_3).text = (date - 11).toString()
            findViewById<Button>(R.id.button_cal_2_4).text =  (date - 10).toString()
            findViewById<Button>(R.id.button_cal_2_5).text = (date - 9).toString()
            findViewById<Button>(R.id.button_cal_2_6).text = (date - 8).toString()
            findViewById<Button>(R.id.button_cal_2_7).text = (date - 7).toString()
            findViewById<Button>(R.id.button_cal_3_1).text = (date - 6).toString()
            findViewById<Button>(R.id.button_cal_3_2).text = (date - 5).toString()
            findViewById<Button>(R.id.button_cal_3_3).text = (date - 4).toString()
            findViewById<Button>(R.id.button_cal_3_4).text =  (date - 3).toString()
            findViewById<Button>(R.id.button_cal_3_5).text = (date - 2).toString()
            findViewById<Button>(R.id.button_cal_3_6).text = (date - 1).toString()
            findViewById<Button>(R.id.button_cal_3_7).text = date.toString()
            findViewById<Button>(R.id.button_cal_4_1).text = (date + 1).toString()
            findViewById<Button>(R.id.button_cal_4_2).text = (date + 2).toString()
            findViewById<Button>(R.id.button_cal_4_3).text = (date + 3).toString()
            findViewById<Button>(R.id.button_cal_4_4).text =  (date + 4).toString()
            findViewById<Button>(R.id.button_cal_4_5).text = (date + 5).toString()
            findViewById<Button>(R.id.button_cal_4_6).text = (date + 6).toString()
            findViewById<Button>(R.id.button_cal_4_7).text = (date + 7).toString()
            findViewById<Button>(R.id.button_cal_5_1).text = (date + 8).toString()
            findViewById<Button>(R.id.button_cal_5_2).text = (date + 9).toString()
            findViewById<Button>(R.id.button_cal_5_3).text = (date + 10).toString()
            findViewById<Button>(R.id.button_cal_5_4).text =  (date + 11).toString()
            findViewById<Button>(R.id.button_cal_5_5).text = (date + 12).toString()
            findViewById<Button>(R.id.button_cal_5_6).text = (date + 13).toString()
            findViewById<Button>(R.id.button_cal_5_7).text = (date + 14).toString()
        }

        fun set_values() {
            val full_name = client_profile.get_first_name() + " " + client_profile.get_last_name()
            val sex = client_profile.get_sex()
            val goal = client_profile.get_goal()
            val weight = client_profile.get_weight()
            val height = client_profile.get_height()
            findViewById<TextView>(R.id.textView_client_name).text = full_name
            findViewById<TextView>(R.id.textView_client_sex).text = sex
            findViewById<TextView>(R.id.textView_client_goal).text = goal
            findViewById<TextView>(R.id.textView_client_weight).text = weight
            findViewById<TextView>(R.id.textView_client_height).text = height
        }

        fun set_workouts()
        {
            //Get Workout List
            val activites = test.Get_Activities(client.get_user_id(), "0")
            var workouts = mutableListOf<Activity>()
            for (i in 0 until activites.size)
            {
                val array = activites[i]
                if(array.get_activity_type() == "WORKOUT")
                {
                    val activity_type = array.get_activity_type()
                    val activity_name = array.get_activity_name()
                    val activity_description = array.get_activity_description()
                    val media_link = array.get_media_link()
                    val assigner = array.get_assigner()
                    val assignee = array.get_assignee()
                    val memo = array.get_memo()
                    val activity_time = array.get_activity_time()
                    val calories = array.get_calories()
                    val activity_id = array.get_activity_id()
                    val completed = array.get_completed()
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
                    workouts.add(temp)
                }
            }
            val adaptor = Activity_Adaptor(workouts.toTypedArray(), this@ClientActivity)
            findViewById<RecyclerView>(R.id.recyclerView_List).layoutManager = LinearLayoutManager(this@ClientActivity)
            findViewById<RecyclerView>(R.id.recyclerView_List).adapter = adaptor
            adaptor.onItemClick = {
                val intent = Intent(this@ClientActivity,ActivityActivity::class.java)
                intent.putExtra("user_object", user)
                intent.putExtra("client_object", client)
                intent.putExtra("activity_object", it)
                startActivity(intent)
                finish()
            }
        }

        // Start of activity here
        set_calendar()
        set_values()
        set_workouts()
        findViewById<ScrollView>(R.id.scrollView_master).smoothScrollTo(0,0)

        primary_button.setOnClickListener {
            findViewById<Button>(R.id.button_primary).setBackgroundResource(R.drawable.button_toggle_active)
            findViewById<Button>(R.id.button_secondary).setBackgroundResource(R.drawable.button_toggle_inactive_right)

            set_workouts()
        }

        secondary_button.setOnClickListener {
            findViewById<Button>(R.id.button_secondary).setBackgroundResource(R.drawable.button_toggle_active)
            findViewById<Button>(R.id.button_primary).setBackgroundResource(R.drawable.button_toggle_inactive_left)

            //Get Meal List
            val activites = test.Get_Activities(client.get_user_id(), "0")
            var meals = mutableListOf<Activity>()
            for (i in 0 until activites.size)
            {
                val array = activites[i]
                if (array.get_activity_type() == "MEAL") {
                    val activity_type = array.get_activity_type()
                    val activity_name = array.get_activity_name()
                    val activity_description = array.get_activity_description()
                    val media_link = array.get_media_link()
                    val assigner = array.get_assigner()
                    val assignee = array.get_assignee()
                    val memo = array.get_memo()
                    val activity_time = array.get_activity_time()
                    val calories = array.get_calories()
                    val activity_id = array.get_activity_id()
                    val completed = array.get_completed()
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
                    meals.add(temp)
                }
            }
            val adaptor = Activity_Adaptor(meals.toTypedArray(), this@ClientActivity)
            findViewById<RecyclerView>(R.id.recyclerView_List).layoutManager = LinearLayoutManager(this@ClientActivity)
            findViewById<RecyclerView>(R.id.recyclerView_List).adapter = adaptor
            adaptor.onItemClick = {
                val intent = Intent(this@ClientActivity,ActivityActivity::class.java)
                intent.putExtra("user_object", user)
                intent.putExtra("client_object", client)
                intent.putExtra("activity_object", it)
                startActivity(intent)
                finish()
            }
        }

        assign_button.setOnClickListener {
            val intent = Intent(this@ClientActivity,AssignActivity::class.java)
            intent.putExtra("user_object", user)
            intent.putExtra("client_object", client)
            startActivity(intent)
            finish()
        }

        back_button.setOnClickListener {
            val intent = Intent(this@ClientActivity,MainActivity::class.java)
            intent.putExtra("user_object", user)
            startActivity(intent)
            finish()
        }

        message_button.setOnClickListener {
            val intent = Intent(this@ClientActivity, MessageActivity::class.java)
            intent.putExtra("user_object", user)
            intent.putExtra("messagee", client.get_user_id())
            intent.putExtra("client_object", client)
            startActivity(intent)
            finish()
        }

        // Calendar Listeners
        expand_button.setOnClickListener {
            if(expand_toggle == 0)
            {
                // Expand
                findViewById<ImageButton>(R.id.imageButton_expand).setImageDrawable(this@ClientActivity.getDrawable(R.drawable.uparrow))
                expand_toggle = 1
                set_expanded_calendar()
                findViewById<LinearLayout>(R.id.week_two).visibility = View.VISIBLE
                findViewById<LinearLayout>(R.id.week_three).visibility = View.VISIBLE
                findViewById<LinearLayout>(R.id.week_four).visibility = View.VISIBLE
                findViewById<LinearLayout>(R.id.week_five).visibility = View.VISIBLE
            }
            else
            {
                // Minify
                findViewById<ImageButton>(R.id.imageButton_expand).setImageDrawable(this@ClientActivity.getDrawable(R.drawable.downarrow))
                expand_toggle = 0
                set_calendar()
                findViewById<LinearLayout>(R.id.week_two).visibility = View.GONE
                findViewById<LinearLayout>(R.id.week_three).visibility = View.GONE
                findViewById<LinearLayout>(R.id.week_four).visibility = View.GONE
                findViewById<LinearLayout>(R.id.week_five).visibility = View.GONE
            }
        }

        button_cal_1_1.setOnClickListener {
            findViewById<Button>(R.id.button_cal_1_1).background = this@ClientActivity.getDrawable(R.drawable.calandar_expand_icon)
            findViewById<Button>(R.id.button_cal_1_2).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_3).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_4).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_5).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_6).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_7).background = this@ClientActivity.getDrawable(R.color.background)
        }

        button_cal_1_2.setOnClickListener {
            findViewById<Button>(R.id.button_cal_1_1).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_2).background = this@ClientActivity.getDrawable(R.drawable.calandar_expand_icon)
            findViewById<Button>(R.id.button_cal_1_3).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_4).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_5).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_6).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_7).background = this@ClientActivity.getDrawable(R.color.background)
        }

        button_cal_1_3.setOnClickListener {
            findViewById<Button>(R.id.button_cal_1_1).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_2).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_3).background = this@ClientActivity.getDrawable(R.drawable.calandar_expand_icon)
            findViewById<Button>(R.id.button_cal_1_4).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_5).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_6).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_7).background = this@ClientActivity.getDrawable(R.color.background)
        }

        button_cal_1_4.setOnClickListener {
            findViewById<Button>(R.id.button_cal_1_1).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_2).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_3).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_4).background = this@ClientActivity.getDrawable(R.drawable.calandar_expand_icon)
            findViewById<Button>(R.id.button_cal_1_5).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_6).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_7).background = this@ClientActivity.getDrawable(R.color.background)
        }

        button_cal_1_5.setOnClickListener {
            findViewById<Button>(R.id.button_cal_1_1).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_2).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_3).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_4).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_5).background = this@ClientActivity.getDrawable(R.drawable.calandar_expand_icon)
            findViewById<Button>(R.id.button_cal_1_6).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_7).background = this@ClientActivity.getDrawable(R.color.background)
        }

        button_cal_1_6.setOnClickListener {
            findViewById<Button>(R.id.button_cal_1_1).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_2).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_3).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_4).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_5).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_6).background = this@ClientActivity.getDrawable(R.drawable.calandar_expand_icon)
            findViewById<Button>(R.id.button_cal_1_7).background = this@ClientActivity.getDrawable(R.color.background)
        }

        button_cal_1_7.setOnClickListener {
            findViewById<Button>(R.id.button_cal_1_1).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_2).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_3).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_4).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_5).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_6).background = this@ClientActivity.getDrawable(R.color.background)
            findViewById<Button>(R.id.button_cal_1_7).background = this@ClientActivity.getDrawable(R.drawable.calandar_expand_icon)
        }
    }
}