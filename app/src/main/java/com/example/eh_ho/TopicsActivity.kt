package com.example.eh_ho

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_topics.*

class TopicsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics)

        val adapter = TopicsAdapter {
            Toast.makeText(this, it.title, Toast.LENGTH_LONG).show()
        }
        adapter.setTopics(TopicsRepo.topics)

        listTopics.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listTopics.adapter = adapter

    }
}