package com.example.eh_ho.data

import java.text.SimpleDateFormat
import java.util.*

object TopicsRepo {
    val topics: MutableList<Topic> = mutableListOf()

    fun getTopic(id: String)= topics.find { it.id == id }

    fun addTopic(title: String, content: String) {
        topics.add(
            Topic(
            title = title,
            content = content
        ))
    }
    fun formatDate(date: String): Date {
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        return formatter.parse(date)
            ?:throw IllegalArgumentException("Date $date has an incorrect format")

    }

    fun dummyTopics(count: Int = 50): List<Topic> {
        return (1..count).map {
            Topic(
                title = "Title ${it}",
                content = "Content ${it}"
            )

        }
    }
}