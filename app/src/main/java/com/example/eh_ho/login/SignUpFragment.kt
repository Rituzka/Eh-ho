package com.example.eh_ho.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eh_ho.R
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.inputEmail

const val MIN_PASS_LENGTH: Int = 10
class SignUpFragment: Fragment() {
    var signUpInteractionListener: SignUpInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is SignUpInteractionListener){
            signUpInteractionListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view){
            labelSignIn.setOnClickListener { goToSignIn() }
            buttonSignUp.setOnClickListener { signUp() }

        }
    }

    private fun goToSignIn() {
      signUpInteractionListener?.onGoToSignIn()
    }

    private fun signUp() {
        if(isFormValid())
        signUpInteractionListener?.onSignUp()
        else
            showFormErrors()
    }

    private fun showFormErrors() {
       if(inputEmail.text?.isEmpty() == true)
           inputEmail.error = getString(R.string.error_empty)
        if(inputUsername.text?.isEmpty() == true)
            inputUsername.error = getString(R.string.error_empty)
        if(inputPassword.text?.isEmpty() == true)
            inputPassword.error = getString(R.string.error_empty)
        if(inputPassword.text?.length ?: 0 <= MIN_PASS_LENGTH)
            inputPassword.error = getString(R.string.error_short)
        if(inputPassword.text?.toString()!= inputConfirmPassword.text?.toString())
            inputConfirmPassword.error = getString(R.string.error_confirm)
    }

    private fun isFormValid() =
        inputEmail.text?.isNotEmpty() ?: false
                && inputUsername.text?.isNotEmpty() ?: false
                && inputPassword.text?.isNotEmpty() ?: false
                && inputPassword.text?.length ?: 0 < MIN_PASS_LENGTH
                && inputPassword.text?.toString() == inputConfirmPassword.text?.toString()

    interface SignUpInteractionListener {
        fun onGoToSignIn()
        fun onSignUp()
    }
}