package com.suitetwentyfour.bodyframe.model

import java.io.Serializable

class Notification: Serializable {
    var notification_id = ""
    var notification = ""
    var notification_date = ""

    fun set_notification_id(notification_id :String) { this.notification_id = notification_id }
    fun set_notification(notification :String) { this.notification = notification }
    fun set_notification_date(notification_date :String) { this.notification_date = notification_date }

    fun get_notification_id() :String { return this.notification_id }
    fun get_notification() :String { return this.notification }
    fun get_notification_date() :String { return this.notification_date }
}