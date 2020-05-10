package com.example.eh_ho.topics

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eh_ho.*
import com.example.eh_ho.data.Topic
import com.example.eh_ho.data.UserRepo
import com.example.eh_ho.login.LoginActivity
import com.example.eh_ho.posts.EXTRA_TOPIC_ID
import com.example.eh_ho.posts.PostsActivity
import com.example.eh_ho.posts.PostsFragment


const val TRANSACTION_CREATE_TOPIC = "create_topic"

class TopicsActivity: AppCompatActivity(),
    TopicsFragment.TopicsInteractionListener,
    CreateTopicFragment.CreateTopicInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, TopicsFragment())
                .commit()
        }
    }


    override fun onTopicSelected(topic: Topic) {
      goToPosts(topic)
    }

    override fun onGoToCreateTopic() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CreateTopicFragment())
            .addToBackStack(TRANSACTION_CREATE_TOPIC)
            .commit()
    }




    override fun onLogOut() {
        UserRepo.logOut(this)

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onTopicCreated() {
        supportFragmentManager.popBackStack()
    }

    private fun goToPosts(topic: Topic) {
        val intent = Intent(this, PostsActivity::class.java)
        intent.putExtra(EXTRA_TOPIC_ID, topic.id)
        startActivity(intent)
    }
}