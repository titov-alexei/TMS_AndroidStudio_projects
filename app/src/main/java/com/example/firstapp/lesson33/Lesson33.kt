package com.example.firstapp.lesson33

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.firstapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Lesson33 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson33)

        var progressBar = findViewById<ProgressBarView>(R.id.progressBar)

        lifecycleScope.launch {
            for(i in 1..100) {
                progressBar.changeProgress(i)
                delay(300)
            }
        }

        progressBar.changeProgress(10)

    }
}