package com.example.eh_ho

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_signing.view.*

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
        signInInteractionListener?.onSignIn()

    }
    private fun goToSignUP() {
        signInInteractionListener?.onGoToSignUp()

    }

    interface SignInInteractionListener {
        fun onGoToSignUp()
        fun onSignIn()
    }

}