package com.example.firstapp.lesson34

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.firstapp.R
import kotlinx.coroutines.launch

class Lesson34 : AppCompatActivity() {

    private lateinit var btnStart: Button
    private lateinit var progressBar: ProgressBarCircle
    private var progress = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson34)

        btnStart = findViewById(R.id.btnStart)
        progressBar = findViewById(R.id.progressBarCircle)
        val miliSecond: Long = 10000

        btnStart.setOnClickListener {
            lifecycleScope.launch {
                val animator = ValueAnimator.ofFloat(0f, 100f).apply {
                    duration = miliSecond
                    addUpdateListener { animation ->
                        progress = animation.animatedValue as Float
                        progressBar.changeProgress(progress)
                        progressBar.setProgress(progress, true)
                        Log.d("MYLOG", "-------$progress")
                    }
                    start()
                }
            }
        }
    }
}