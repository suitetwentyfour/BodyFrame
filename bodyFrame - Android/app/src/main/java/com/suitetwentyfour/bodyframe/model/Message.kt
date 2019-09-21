package com.suitetwentyfour.bodyframe.model

import java.io.Serializable
import java.lang.Boolean.FALSE

class Message : Serializable {
    var user_id = ""
    var message_from = ""
    var message_date = ""
    var message = ""
    var message_id = ""
    var read = ""
    var my_message = FALSE

    fun set_user_id(user_id :String) { this.user_id = user_id }
    fun set_message_from(message_from :String) { this.message_from = message_from }
    fun set_message_date(message_date :String) { this.message_date = message_date }
    fun set_message(message :String) { this.message = message }
    fun set_message_id(message_id :String) { this.message_id = message_id }
    fun set_read(read :String) { this.read = read }
    fun is_mine(mine :Boolean) { this.my_message = mine }

    fun get_user_id() :String { return this.user_id }
    fun get_message_from() :String { return this.message_from }
    fun get_message_date() :String { return this.message_date }
    fun get_message() :String { return this.message }
    fun get_message_id() :String { return this.message_id }
    fun get_read() :String { return this.read }
    fun is_mine() :Boolean { return this.my_message }
}