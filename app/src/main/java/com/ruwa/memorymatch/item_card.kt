package com.ruwa.memorymatch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

data class Card(val imageResId: Int)

class CardAdapter(private val context: Context, private val cards: List<Card>) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val flippedCards = MutableList(cards.size) { false }

    override fun getCount(): Int {
        return cards.size
    }

    override fun getItem(position: Int): Any {
        return cards[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // ViewHolder pattern for better performance
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.activity_item_card, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val card = cards[position]
        if (flippedCards[position]) {
            viewHolder.imageView.setImageResource(card.imageResId)
        } else {
            viewHolder.imageView.setImageResource(R.drawable.card_back)
        }

        return view
    }

    fun flipCard(position: Int) {
        flippedCards[position] = !flippedCards[position]
        notifyDataSetChanged()
    }

    fun allCardsFlipped(): Boolean {
        return flippedCards.all { it }
    }

    private class ViewHolder(view: View) {
        val imageView: ImageView = view.findViewById(R.id.card_image)
    }
}
