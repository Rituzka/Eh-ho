package com.example.eh_ho.login
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eh_ho.R
import com.example.eh_ho.topics.TopicsActivity

class LoginActivity : AppCompatActivity(),
    SigningFragment.SignInInteractionListener,
    SignUpFragment.SignUpInteractionListener {

    val signInFragment: SigningFragment =
        SigningFragment()
    val signUpFragment: SignUpFragment =
        SignUpFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, signInFragment)
                .commit()
        }
}

    override fun onGoToSignUp() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, signUpFragment)
            .commit()
    }

    override fun onSignIn() {
      onGoToSignIn()
    }

    override fun onGoToSignIn() {
        launchTopicsActivity()
    }

    override fun onSignUp() {
        launchTopicsActivity()
    }

    private fun launchTopicsActivity() {
        val intent: Intent = Intent(this, TopicsActivity::class.java)
        startActivity(intent)
    }
}
