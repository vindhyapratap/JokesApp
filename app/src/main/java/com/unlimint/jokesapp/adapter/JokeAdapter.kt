package com.unlimint.jokesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.unlimint.jokesapp.R
import com.unlimint.jokesapp.models.Joke

class JokeAdapter(private val jokes: List<Joke>) :
    RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {

    class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val jokeTextView: TextView = itemView.findViewById(R.id.tv_joke_view)

        fun bind(joke: Joke) {
            jokeTextView.text = joke.joke
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_joke_layout, parent, false)
        return JokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(jokes[position])
    }

    override
    fun getItemCount(): Int{
        return jokes.size
    }
}