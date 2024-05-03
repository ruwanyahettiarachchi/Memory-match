package com.ruwa.memorymatch

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HighScoresActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private var highestScore: Int = 0
    private var movesCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscores)

        sharedPreferences = getSharedPreferences("MemoryMatchGame", MODE_PRIVATE)

        // Get highest score from SharedPreferences
        highestScore = sharedPreferences.getInt("highestScore", 0)

        // Get moves count from intent
        movesCount = intent.getIntExtra("movesCount", 0)

        // Update highest score if current score is higher
        if (movesCount < highestScore || highestScore == 0) {
            highestScore = movesCount
            val editor = sharedPreferences.edit()
            editor.putInt("highestScore", highestScore)
            editor.apply()
        }

        // Display highest score
        val highestScoreTextView: TextView = findViewById(R.id.highest_score_text_view)
        highestScoreTextView.text = "Highest Score: $highestScore moves"

        // Display current score
        val currentScoreTextView: TextView = findViewById(R.id.current_score_text_view)
        currentScoreTextView.text = "Your Score: $movesCount moves"

        val getHomeButton = findViewById<Button>(R.id.btn_home)

        getHomeButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
