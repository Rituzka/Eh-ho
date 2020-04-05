package com.example.eh_ho


import com.example.eh_ho.data.Post
import org.json.JSONObject
import org.junit.Test
import java.util.*
import org.junit.Assert.*
import java.text.SimpleDateFormat

class PostModelTest {
    @Test
    fun getOffset_year_isCorrect() {
        val dateToCompare = formatDate("04/04/2020 18:38:29")
        val testPost = Post(topicId= "580", username = "Tushe2",date =  formatDate("04/04/2019 18:38:29"),
            title = "Topic 1 - Postman", content = "Topic 1 - Postman Topic 1 - Postman Topic 1 - Postman")

        val offset = testPost.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 1, offset.amount)
        assertEquals("Unit Comparison", Calendar.YEAR, offset.unit)

    }
    @Test
    fun getOffset_month_isCorrect() {
        val dateToCompare = formatDate("04/04/2020 18:38:29")
        val testPost = Post(topicId= "580", username = "Tushe2",date =  formatDate("04/03/2020 18:38:29"),
            title = "Topic 1 - Postman", content = "Topic 1 - Postman Topic 1 - Postman Topic 1 - Postman")

        val offset = testPost.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 1, offset.amount)
        assertEquals("Unit Comparison", Calendar.MONTH, offset.unit)

    }

    @Test
    fun getOffset_day_isCorrect() {
        val dateToCompare = formatDate("04/04/2020 18:38:29")
        val testPost = Post(topicId= "580", username = "Tushe2",date =  formatDate("03/04/2020 18:38:29"),
            title = "Topic 1 - Postman", content = "Topic 1 - Postman Topic 1 - Postman Topic 1 - Postman")

        val offset = testPost.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 1, offset.amount)
        assertEquals("Unit Comparison", Calendar.DAY_OF_MONTH, offset.unit)

    }

    @Test
    fun getOffset_hour_isCorrect() {
        val dateToCompare = formatDate("04/04/2020 18:38:29")
        val testPost = Post(topicId= "580", username = "Tushe2",date =  formatDate("04/04/2020 17:38:29"),
            title = "Topic 1 - Postman", content = "Topic 1 - Postman Topic 1 - Postman Topic 1 - Postman")

        val offset = testPost.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 1, offset.amount)
        assertEquals("Unit Comparison", Calendar.HOUR, offset.unit)

    }

    @Test
    fun getOffset_minute_isCorrect() {
        val dateToCompare = formatDate("04/04/2020 18:38:29")
        val testPost = Post(topicId= "580", username = "Tushe2",date =  formatDate("04/04/2020 18:37:29"),
            title = "Topic 1 - Postman", content = "Topic 1 - Postman Topic 1 - Postman Topic 1 - Postman")

        val offset = testPost.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 1, offset.amount)
        assertEquals("Unit Comparison", Calendar.MINUTE, offset.unit)

    }
    @Test
    fun getOffset_seconds_isCorrect() {
        val dateToCompare = formatDate("04/04/2020 18:38:29")
        val testPost = Post(topicId= "580", username = "Tushe2",date =  formatDate("04/04/2020 18:38:19"),
            title = "Topic 1 - Postman", content = "Topic 1 - Postman Topic 1 - Postman Topic 1 - Postman")

        val offset = testPost.getTimeOffset(dateToCompare)

        assertEquals("Amount Comparison", 0, offset.amount)
        assertEquals("Unit Comparison", Calendar.MINUTE, offset.unit)

    }
    @Test
    fun parsePostTest() {
        val testPost = Post(topicId= "580", username = "Tushe2",date =  formatDate("04/04/2020 18:38:19"),
            title = "Topic 1 - Postman", content = "Topic 1 - Postman Topic 1 - Postman Topic 1 - Postman")
        val jsonTest =
            "{\"id\": 938, \"topic_id\":\"580\", \"username\":\"Tushe2\", \"topic_title\":\"Topic 1 - Postman\"," +
                    "\"created_at\":\"2020-04-04T18:38:19.122Z\",\"raw\": \"Topic 1 - Postman Topic 1 - Postman Topic 1 - Postman\"}"
        val json = JSONObject(jsonTest)

        val post = Post.parsePost(json)

        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val dateFormatted = formatter.format(post.date)

        assertEquals("938", post.id)
        assertEquals("580", post.topicId)
        assertEquals("Tushe2", post.username)
        assertEquals("04/04/2020 08:38:19", dateFormatted)
        assertEquals("Topic 1 - Postman", post.title)
        assertEquals("Topic 1 - Postman Topic 1 - Postman Topic 1 - Postman", post.content)

    }

    private fun formatDate(date: String): Date {
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        return formatter.parse(date) ?: throw IllegalArgumentException("date incorrect")

    }
}