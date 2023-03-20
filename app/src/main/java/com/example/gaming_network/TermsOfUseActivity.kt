package com.example.gaming_network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class TermsOfUseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_of_use)

        Toast.makeText(applicationContext,"Hi",Toast.LENGTH_SHORT).show()
    }
}