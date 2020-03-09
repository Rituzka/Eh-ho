package com.example.eh_ho

object TopicsRepo {
    val topics: MutableList<Topic> = mutableListOf()
    get() {
        if(field.isEmpty())
            field.addAll(dummyTopics())
        return field
    }

    fun getTopic(id: String)= topics.find { it.id == id }

    fun dummyTopics(count: Int = 50): List<Topic> {
        return (1..count).map { Topic(title = "Title ${it}", content = "Content ${it}")

        }
    }
}