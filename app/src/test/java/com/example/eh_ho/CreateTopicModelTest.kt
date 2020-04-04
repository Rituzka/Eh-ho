package com.example.eh_ho

import com.example.eh_ho.data.CreateTopicModel
import org.junit.Test
import org.junit.Assert.*

class CreateTopicModelTest {
    @Test
    fun ToJson_isCorrect() {
        val model = CreateTopicModel("Hola", "Mi primer content")
        val json = model.toJson()

        assertEquals("Hola", json.get("title") )
        assertEquals("Mi primer content", json.get("raw"))

    }
}

