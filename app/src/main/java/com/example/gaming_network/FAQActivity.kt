package com.example.gaming_network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.gaming_network.databinding.ActivityFaqactivityBinding
import com.example.gaming_network.databinding.ActivityMainBinding

class FAQActivity : AppCompatActivity() {

    private lateinit var faqBinding: ActivityFaqactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        faqBinding = ActivityFaqactivityBinding.inflate(layoutInflater)
        val view = faqBinding.root
        setContentView(view)

        val but1 = faqBinding.faq1
        val but1ans = faqBinding.faq1Ans

        but1.setOnClickListener {
            if (but1ans.visibility == View.VISIBLE) {
                but1ans.visibility = View.GONE;
            }
            else {
                but1ans.visibility = View.VISIBLE;
            }
        }
    }
}