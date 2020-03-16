package com.example.eh_ho

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_sign_up.view.*
import kotlinx.android.synthetic.main.fragment_signing.view.*

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
        signUpInteractionListener?.onSignUp()
    }

    interface SignUpInteractionListener {
        fun onGoToSignIn()
        fun onSignUp()
    }
}