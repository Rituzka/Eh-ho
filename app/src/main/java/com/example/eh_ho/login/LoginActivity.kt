package com.example.eh_ho.login
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.eh_ho.R
import com.example.eh_ho.data.RequestError
import com.example.eh_ho.data.SignInModel
import com.example.eh_ho.data.SignUpModel
import com.example.eh_ho.data.UserRepo
import com.example.eh_ho.topics.TopicsActivity
import com.google.android.material.snackbar.Snackbar
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

        if (savedInstanceState == null) {
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

    private fun enableLoading(enabled: Boolean) {
        if (enabled) {
            fragmentContainer.visibility = View.INVISIBLE
            viewLoading.visibility = View.VISIBLE
        } else {
            fragmentContainer.visibility = View.VISIBLE
            viewLoading.visibility = View.INVISIBLE
        }
    }

    override fun onSignIn(username: SignInModel) {
        enableLoading(true)
        UserRepo.signIn(this,
            username,
            {
                enableLoading(false)
                launchTopicsActivity()
            },
            {
                enableLoading(false)
                handleRequestError(it)
            })
    }

    override fun onGoToSignIn() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, signInFragment)
            .commit()
    }

    override fun onSignUp(signUpModel: SignUpModel) {
        enableLoading(true)
        UserRepo.signUp(this,
            signUpModel,
            {
                enableLoading(false)
                launchTopicsActivity()
            },
            {
                enableLoading(false)
                handleRequestError(it)
            })
    }

    private fun handleRequestError(requestError: RequestError) {
       val message =  if(requestError.messageResId != null)
            getString(requestError.messageResId)
        else if (requestError.message != null)
           requestError.message
        else
       getString(R.string.error_request_default)
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show()
    }

    private fun launchTopicsActivity() {
        val intent = Intent(this, TopicsActivity::class.java)
        startActivity(intent)
        finish()
    }
}
