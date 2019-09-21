package com.suitetwentyfour.bodyframe.model

import java.io.Serializable

class User : Serializable {
    var user_id = ""
    var user_type = ""
    var user_email = ""
    var assigned_to = ""
    var member_since = ""
    var paid_until = ""

    fun set_user_id(user_id :String) { this.user_id = user_id }
    fun set_user_type(user_type :String) { this.user_type = user_type }
    fun set_user_email(user_email :String) { this.user_email = user_email }
    fun set_assigned_to(assigned_to :String) { this.assigned_to = assigned_to }
    fun set_member_since(member_since :String) { this.member_since = member_since }
    fun set_paid_until(paid_until :String) { this.paid_until = paid_until }

    fun get_user_id() :String { return this.user_id }
    fun get_user_type() :String { return this.user_type }
    fun get_user_email() :String { return this.user_email }
    fun get_assigned_to() :String { return this.assigned_to }
    fun get_member_since() :String { return this.member_since }
    fun get_paid_until() :String { return this.paid_until }
}