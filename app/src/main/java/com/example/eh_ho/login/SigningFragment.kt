package com.example.eh_ho.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eh_ho.R
import kotlinx.android.synthetic.main.fragment_signing.*
import kotlinx.android.synthetic.main.fragment_signing.view.*
import kotlinx.android.synthetic.main.fragment_signing.view.buttonLogin

class SigningFragment: Fragment() {
    var signInInteractionListener: SignInInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SignInInteractionListener)
            signInInteractionListener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      return inflater.inflate(R.layout.fragment_signing, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with (view) {
            labelCreateAccount.setOnClickListener { goToSignUP() }
            buttonLogin.setOnClickListener { signIn() }
        }
    }
    private fun signIn() {
        if(isFormValid())
        signInInteractionListener?.onSignIn(inputUsername.text.toString())
        else
            showFormErrors()

    }

    private fun showFormErrors() {
        if(inputUsername.text?.isEmpty() == true)
            inputUsername.error = getString(R.string.error_empty)
        if(inputPassword.text?.isEmpty() == true)
            inputPassword.error = getString(R.string.error_empty)
    }

    private fun isFormValid() =
        inputUsername.text?.isNotEmpty() ?: false &&
                inputPassword.text?.isNotEmpty() ?: false

    private fun goToSignUP() {
        signInInteractionListener?.onGoToSignUp()

    }

    interface SignInInteractionListener {
        fun onGoToSignUp()
        fun onSignIn(username: String)
    }

}