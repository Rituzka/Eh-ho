package com.example.eh_ho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_posts.*

const val EXTRA_TOPIC_ID = "topicId"

class PostsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        val topicId = intent.getStringExtra(EXTRA_TOPIC_ID)
        val topic = TopicsRepo.getTopic(topicId)

        labelTitle.text = topic?.title
        //34:51

    }
}
