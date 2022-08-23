package com.vishal.kotlin_github.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vishal.kotlin_github.databinding.ActivityLoginBinding
import com.vishal.kotlin_github.viewmodel.ProfileViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var username: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ON-BUTTON CLICK EVENTS
        binding.button.setOnClickListener() {
            val username: String = binding.username.text.toString()

            if(binding.username.text.isEmpty())
                Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
            else {
                startActivity(Intent(this, ProfileActivity::class.java)
                    .putExtra("username", username))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("username", username)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        username = savedInstanceState.getString("username").toString()
    }
}