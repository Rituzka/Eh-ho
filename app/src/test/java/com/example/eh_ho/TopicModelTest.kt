package com.example.eh_ho

import com.example.eh_ho.data.Topic
import com.example.eh_ho.data.TopicsRepo
import org.json.JSONObject
import org.junit.Test
import java.util.*
import org.junit.Assert.*
import java.text.SimpleDateFormat

class TopicModelTest {
    @Test
    fun getOffset_year_isCorrect() {
        val dateToCompare = formatDate("01/01/2020 10:00:00")
        val testTopic = Topic(title="Test", date =  formatDate("01/01/2019 10:00:00"))

        val offset = testTopic.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 1, offset.amount)
        assertEquals("Unit Comparison", Calendar.YEAR, offset.unit)

    }
    @Test
    fun getOffset_month_isCorrect() {
        val dateToCompare = formatDate("01/02/2019 10:00:00")
        val testTopic = Topic(title="Test", date = formatDate("01/01/2019 10:00:00"))

        val offset = testTopic.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 1, offset.amount)
        assertEquals("Unit Comparison", Calendar.MONTH, offset.unit)

    }

    @Test
    fun getOffset_day_isCorrect() {
        val dateToCompare = formatDate("20/02/2019 10:00:00")
        val testTopic = Topic(title="Test", date =  formatDate("19/02/2019 10:00:00"))

        val offset = testTopic.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 1, offset.amount)
        assertEquals("Unit Comparison", Calendar.DAY_OF_MONTH, offset.unit)

    }

    @Test
    fun getOffset_hour_isCorrect() {
        val dateToCompare = formatDate("20/02/2019 11:00:00")
        val testTopic = Topic(title="Test", date =  formatDate("20/02/2019 10:00:00"))

        val offset = testTopic.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 1, offset.amount)
        assertEquals("Unit Comparison", Calendar.HOUR, offset.unit)

    }

    @Test
    fun getOffset_minute_isCorrect() {
        val dateToCompare = formatDate("20/02/2019 10:10:00")
        val testTopic = Topic(title="Test", date =  formatDate("20/02/2019 10:09:00"))

        val offset = testTopic.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 1, offset.amount)
        assertEquals("Unit Comparison", Calendar.MINUTE, offset.unit)

    }
    @Test
    fun getOffset_seconds_isCorrect() {
        val dateToCompare = formatDate("20/02/2019 10:10:10")
        val testTopic = Topic(title="Test", date =  formatDate("20/02/2019 10:10:09"))

        val offset = testTopic.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 0, offset.amount)
        assertEquals("Unit Comparison", Calendar.MINUTE, offset.unit)

    }
    @Test
     fun parseTopicTest() {

        val jsonTest =
            "{\"id\": 1, \"title\":\"hola\",\"created_at\":\"2019-12-12T01:41:28.809Z\",\"posts_count\": 1,\"views\": 29}"
        val json = JSONObject(jsonTest)

        val topic = Topic.parseTopic(json)

        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val dateFormatted = formatter.format(topic.date)

        assertEquals("1", topic.id)
        assertEquals("hola", topic.title)
        assertEquals(1, topic.posts)
        assertEquals(29, topic.views)
        assertEquals("12/12/2019 02:41:28", dateFormatted)
    }

    private fun formatDate(date: String): Date {
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        return formatter.parse(date) ?: throw IllegalArgumentException("date incorrect")

    }
}