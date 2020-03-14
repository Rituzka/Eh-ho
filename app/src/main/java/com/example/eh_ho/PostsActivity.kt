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
        if(topicId != null && topicId.isNotEmpty()){
            val topic = TopicsRepo.getTopic(topicId)
            //en caso que el topic no sea nulo ejecuta el cuerpo
            topic?.let {
                labelTitle.text = topic?.title
            }
        }else {
            throw IllegalArgumentException("You should provide an id for the topic")
        }
    }
}
