package com.example.eh_ho

import com.example.eh_ho.data.Topic
import com.example.eh_ho.data.TopicsRepo
import org.junit.Test
import java.util.*
import org.junit.Assert.*

class TopicModelTest {
    @Test
    fun getOffset_year_isCorrect() {
        val dateToCompare = TopicsRepo.formatDate("01/01/2020 10:00:00")
        val testTopic = Topic(title="Test", content = "Test Content", date =  TopicsRepo.formatDate("01/01/2019 10:00:00"))

        val offset = testTopic.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 1, offset.amount)
        assertEquals("Unit Comparison", Calendar.YEAR, offset.unit)

    }
    @Test
    fun getOffset_month_isCorrect() {
        val dateToCompare = TopicsRepo.formatDate("01/02/2019 10:00:00")
        val testTopic = Topic(title="Test", content = "Test Content", date =  TopicsRepo.formatDate("01/01/2019 10:00:00"))

        val offset = testTopic.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 1, offset.amount)
        assertEquals("Unit Comparison", Calendar.MONTH, offset.unit)

    }

    @Test
    fun getOffset_day_isCorrect() {
        val dateToCompare = TopicsRepo.formatDate("20/02/2019 10:00:00")
        val testTopic = Topic(title="Test", content = "Test Content", date =  TopicsRepo.formatDate("19/02/2019 10:00:00"))

        val offset = testTopic.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 1, offset.amount)
        assertEquals("Unit Comparison", Calendar.DAY_OF_MONTH, offset.unit)

    }

    @Test
    fun getOffset_hour_isCorrect() {
        val dateToCompare = TopicsRepo.formatDate("20/02/2019 11:00:00")
        val testTopic = Topic(title="Test", content = "Test Content", date =  TopicsRepo.formatDate("20/02/2019 10:00:00"))

        val offset = testTopic.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 1, offset.amount)
        assertEquals("Unit Comparison", Calendar.HOUR, offset.unit)

    }

    @Test
    fun getOffset_minute_isCorrect() {
        val dateToCompare = TopicsRepo.formatDate("20/02/2019 10:10:00")
        val testTopic = Topic(title="Test", content = "Test Content", date =  TopicsRepo.formatDate("20/02/2019 10:09:00"))

        val offset = testTopic.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 1, offset.amount)
        assertEquals("Unit Comparison", Calendar.MINUTE, offset.unit)

    }
    @Test
    fun getOffset_seconds_isCorrect() {
        val dateToCompare = TopicsRepo.formatDate("20/02/2019 10:10:10")
        val testTopic = Topic(title="Test", content = "Test Content", date =  TopicsRepo.formatDate("20/02/2019 10:10:09"))

        val offset = testTopic.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 0, offset.amount)
        assertEquals("Unit Comparison", Calendar.MINUTE, offset.unit)

    }



}