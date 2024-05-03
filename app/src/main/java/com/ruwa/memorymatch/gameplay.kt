package com.ruwa.memorymatch

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class GameplayActivity : AppCompatActivity() {
    private lateinit var gridView: GridView
    private lateinit var adapter: CardAdapter
    private lateinit var cards: List<Card>
    private var firstCardPosition: Int? = null
    private var secondCardPosition: Int? = null
    private var movesCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameplay)

        cards = generateCards()

        gridView = findViewById(R.id.grid_view)
        adapter = CardAdapter(this, cards)
        gridView.adapter = adapter

        // Set item click listener for grid view
        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // Handle card click event
            handleCardClick(position)
        }
    }

    private fun generateCards(): List<Card> {
        // Generate a list of cards with pairs of matching images
        val cards = mutableListOf<Card>()
        // Add your logic to generate pairs of matching images
        // For simplicity, I'll just add 8 pairs of dummy cards
        repeat(2) {
            cards.add(Card(R.drawable.card1))
            cards.add(Card(R.drawable.card2))
            cards.add(Card(R.drawable.card3))
            cards.add(Card(R.drawable.card4))
            cards.add(Card(R.drawable.card5))
            cards.add(Card(R.drawable.card6))
            cards.add(Card(R.drawable.card7))
            cards.add(Card(R.drawable.card8))
        }
        cards.shuffle() // Shuffle the cards
        return cards
    }

    private fun handleCardClick(position: Int) {
        if (firstCardPosition == null) {
            // First card clicked
            firstCardPosition = position
            adapter.flipCard(position)
        } else if (secondCardPosition == null) {
            // Second card clicked
            secondCardPosition = position
            adapter.flipCard(position)

            // Check if the two flipped cards match
            if (cards[firstCardPosition!!].imageResId == cards[secondCardPosition!!].imageResId) {
                // Cards match
                // Implement your logic for when cards match
                // For example, keep them face up and increment score
                // Reset the first and second card positions
                firstCardPosition = null
                secondCardPosition = null
                // Increment moves count
                movesCount++
                // Check if game is complete
                if (isGameComplete()) {
                    // Game over, navigate to high scores screen
                    val intent = Intent(this, HighScoresActivity::class.java)
                    intent.putExtra("movesCount", movesCount)
                    startActivity(intent)
                    finish()
                }
            } else {
                // Cards do not match
                // Implement your logic for when cards do not match
                // For example, flip them back face down after a short delay
                Handler().postDelayed({
                    adapter.flipCard(firstCardPosition!!)
                    adapter.flipCard(secondCardPosition!!)
                    // Reset the first and second card positions
                    firstCardPosition = null
                    secondCardPosition = null
                }, 1000) // Delay in milliseconds before flipping cards back
                // Increment moves count
                movesCount++
            }
        }
    }

    private fun isGameComplete(): Boolean {
        // Check if all cards are flipped
        return adapter.allCardsFlipped()
    }
}
