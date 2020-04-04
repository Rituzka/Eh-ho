package com.example.eh_ho.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eh_ho.R
import com.example.eh_ho.data.TopicsRepo
import kotlinx.android.synthetic.main.activity_posts.*

const val EXTRA_TOPIC_ID = "topicId"

class PostsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        val topicId = intent.getStringExtra(EXTRA_TOPIC_ID)
        if(topicId != null && topicId.isNotEmpty()){
            //en caso que el topic no sea nulo ejecuta el cuerpo
        }else {
            throw IllegalArgumentException("You should provide an id for the topic")
        }
    }
}
