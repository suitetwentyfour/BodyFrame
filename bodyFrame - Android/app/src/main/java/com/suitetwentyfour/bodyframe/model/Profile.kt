package com.suitetwentyfour.bodyframe.model

import java.io.Serializable

class Profile : Serializable {
    var first_name = ""
    var last_name = ""
    var birth_day = ""
    var address = ""
    var phone_number = ""
    var sex = ""
    var goal = ""
    var weight = ""
    var height = ""
    var profile_picture = ""

    fun set_first_name(first_name :String) { this.first_name = first_name }
    fun set_last_name(last_name :String) { this.last_name = last_name }
    fun set_birth_day(birth_day :String) { this.birth_day = birth_day }
    fun set_address(address :String) { this.address = address }
    fun set_phone_number(phone_number :String) { this.phone_number = phone_number }
    fun set_sex(sex :String) { this.sex = sex }
    fun set_goal(goal :String) { this.goal = goal }
    fun set_weight(weight :String) { this.weight = weight }
    fun set_height(height :String) { this.height = height }
    fun set_profile_picture(link :String) { this.profile_picture = link }

    fun get_first_name() :String { return this.first_name }
    fun get_last_name() :String { return this.last_name }
    fun get_birth_day() :String { return this.birth_day }
    fun get_address() :String { return this.address }
    fun get_phone_number() :String { return this.phone_number }
    fun get_sex() :String { return this.sex }
    fun get_goal() :String { return this.goal }
    fun get_weight() :String { return this.weight }
    fun get_height() :String { return this.height }
    fun get_profile_picture() :String { return this.profile_picture }
}