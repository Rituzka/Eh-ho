package com.example.eh_ho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button: Button = findViewById(R.id.buttonLogin)
        val listener: ((View) -> Unit) = { view: View ->
            //recuperar el username
            val username: String = findViewById<EditText>(R.id.inputUsername).text.toString()
            //mostramos el mensaje
            Toast.makeText(this, "Welcome to Eh-ho $(username)", Toast.LENGTH_SHORT).show()

        }
        button.setOnClickListener { listener }
}
}
