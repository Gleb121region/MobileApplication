package ru.spbstu.mobileapplication.presentation.interview.fragments.city

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.spbstu.mobileapplication.R

class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cityNameTextView: TextView = itemView.findViewById(R.id.cityNameTextView)
}