package com.suitetwentyfour.bodyframe.model

import java.io.Serializable

class Activity : Serializable {
    var activity_type = ""
    var activity_name = ""
    var activity_description = ""
    var media_link = ""
    var assigner = ""
    var assignee = ""
    var memo = ""
    var activity_time = ""
    var calories = ""
    var activity_id = ""
    var completed = ""

    fun set_activity_type(activity_type :String) { this.activity_type = activity_type }
    fun set_activity_name(activity_name :String) { this.activity_name = activity_name }
    fun set_activity_description(activity_description :String) { this.activity_description = activity_description }
    fun set_media_link(media_link :String) { this.media_link = media_link }
    fun set_assigner(assigner :String) { this.assigner = assigner }
    fun set_assignee(assignee :String) { this.assignee = assignee }
    fun set_memo(memo :String) { this.memo = memo }
    fun set_activity_time(activity_time :String) { this.activity_time = activity_time }
    fun set_calories(calories :String) { this.calories = calories }
    fun set_activity_id(activity_id :String) { this.activity_id = activity_id }
    fun set_completed(completed :String) { this.completed = completed }

    fun get_activity_type() :String { return this.activity_type }
    fun get_activity_name() :String { return this.activity_name }
    fun get_activity_description() :String { return this.activity_description }
    fun get_media_link() :String { return this.media_link }
    fun get_assigner() :String { return this.assigner }
    fun get_assignee() :String { return this.assignee }
    fun get_memo() :String { return this.memo }
    fun get_activity_time() :String { return this.activity_time }
    fun get_calories() :String { return this.calories }
    fun get_activity_id() :String { return this.activity_id }
    fun get_completed() :String { return this.completed }
}