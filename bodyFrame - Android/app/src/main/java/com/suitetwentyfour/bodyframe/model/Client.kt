package com.suitetwentyfour.bodyframe.model

import java.io.Serializable

class Client : Serializable {
    var user_id = ""
    var first_name = ""
    var last_name = ""

    fun set_user_id(user_id :String) { this.user_id = user_id }
    fun set_first_name(first_name :String) { this.first_name = first_name }
    fun set_last_name(last_name :String) { this.last_name = last_name }

    fun get_user_id() :String { return this.user_id }
    fun get_first_name() :String { return this.first_name }
    fun get_last_name() :String { return this.last_name }
}