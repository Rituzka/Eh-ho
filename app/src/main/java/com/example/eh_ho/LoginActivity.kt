package com.example.eh_ho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),
    SigningFragment.SignInInteractionListener,
    SignUpFragment.SignUpInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, SigningFragment())
                .commit()
        }
}

    fun login(view:View) {
        val intent: Intent = Intent(this, TopicsActivity::class.java)
        startActivity(intent)

    }

    override fun onGoToSignUp() {

    }

    override fun onSignIn() {

    }

    override fun onGoToSignIn() {

    }

    override fun onSignUp() {

    }
}
