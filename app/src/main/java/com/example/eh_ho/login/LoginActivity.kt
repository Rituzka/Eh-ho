package com.example.eh_ho.login
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.eh_ho.R
import com.example.eh_ho.data.UserRepo
import com.example.eh_ho.topics.TopicsActivity
import kotlinx.android.synthetic.main.activity_login.*

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
           checkSession()
        }
}

    private fun checkSession() {
        if (UserRepo.isLogged(this))
            launchTopicsActivity()
        else
            onGoToSignIn()
    }

    override fun onGoToSignUp() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, signUpFragment)
            .commit()
    }

    fun enableLoading(enabled: Boolean) {
        if (enabled) {
            fragmentContainer.visibility = View.INVISIBLE
            viewLoading.visibility = View.VISIBLE
        } else {
            fragmentContainer.visibility = View.VISIBLE
            viewLoading.visibility = View.INVISIBLE
        }
    }

    override fun onSignIn(username: String) {
        enableLoading(true)
        UserRepo.signIn(this, username )
        simulateLoading()
    }

    override fun onGoToSignIn() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, signInFragment)
            .commit()
    }

    override fun onSignUp() {
        enableLoading(true)
        simulateLoading()

    }

    private fun launchTopicsActivity() {
        val intent: Intent = Intent(this, TopicsActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun simulateLoading() {
        val runnable = Runnable {
            Thread.sleep(3000)
            viewLoading.post {
            launchTopicsActivity()
            }
        }
        Thread(runnable).start()
    }

    private fun simulateLoadingAsyncTask() {
        val task = object : AsyncTask<Void, Void, Boolean>() {
            override fun doInBackground(vararg p0: Void?): Boolean {
                Thread.sleep(3 * 1000)
                return true
            }
            override fun onPostExecute(result: Boolean?) {
                super.onPostExecute(result)
                launchTopicsActivity()
            }
        }
        task.execute()
    }
}
