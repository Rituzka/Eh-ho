package com.example.eh_ho.data

import android.icu.util.Calendar
import java.util.*

data class Topic (
    val id:String =  UUID.randomUUID().toString(),
    val title: String,
    val content:String,
    val date: Date = Date(),
    val posts: Int = 0,
    val views: Int = 0
) {
    companion object {
        const val MINUTES_MILLIS = 1000L*60
        const val HOUR_MILLIS = MINUTES_MILLIS*60
        const val DAY_MILLIS = HOUR_MILLIS*24
        const val MONTH_MILLIS = DAY_MILLIS*30
        const val YEAR_MILLIS = MONTH_MILLIS*12
    }

    data class TimeOffset(val amount: Int, val unit: Int)

    fun getTimeOffset(dateToCompare:Date = Date()): TimeOffset{
        val current = dateToCompare.time
        val diff = current - date.time

        val years = diff / YEAR_MILLIS
        if (years > 0) return TimeOffset(years.toInt(), Calendar.YEAR)

        val month = diff / MONTH_MILLIS
        if (month > 0) return TimeOffset(month.toInt(), Calendar.MONTH)

        val days = diff / DAY_MILLIS
        if(days > 0) return TimeOffset(days.toInt(), Calendar.DAY_OF_MONTH)

        val hours = diff / HOUR_MILLIS
        if(hours > 0) return TimeOffset(hours.toInt(), Calendar.HOUR)

        val minutes = diff / MINUTES_MILLIS
        if(minutes > 0) return TimeOffset(minutes.toInt(), Calendar.MINUTE)

        return TimeOffset(0, Calendar.MINUTE)


    }
}