package com.ruwa.memorymatch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton: Button = findViewById(R.id.start_button)
        val highScoresButton: Button = findViewById(R.id.high_scores_button)

        startButton.setOnClickListener {
            startActivity(Intent(this, GameplayActivity::class.java))
        }

        highScoresButton.setOnClickListener {
            startActivity(Intent(this, HighScoresActivity::class.java))
        }
    }
}

