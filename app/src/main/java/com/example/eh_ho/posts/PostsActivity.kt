package com.example.eh_ho.posts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eh_ho.R
import com.example.eh_ho.data.Post
import com.example.eh_ho.data.UserRepo
import com.example.eh_ho.login.LoginActivity


const val EXTRA_TOPIC_ID = "topicId"
const val TRANSACTION_CREATE_POST = "create_post"

class PostsActivity : AppCompatActivity(),
PostsFragment.PostsInteractionListener,
CreatePostFragment.CreatePostInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        val topicId = intent.getStringExtra(EXTRA_TOPIC_ID)
        if(topicId != null && topicId.isNotEmpty()){
            //en caso que el topic no sea nulo ejecuta el cuerpo

                supportFragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, PostsFragment())
                    .commit()


        }else {
            throw IllegalArgumentException("You should provide an id for the topic")
        }
    }

    override fun onPostSelected(post: Post) {

    }

    override fun onGoToCreatePost() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CreatePostFragment())
            .addToBackStack(TRANSACTION_CREATE_POST)
            .commit()
    }

    override fun onLogOut() {
        UserRepo.logOut(this)

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onPostCreated() {
    supportFragmentManager.popBackStack()
    }
}
