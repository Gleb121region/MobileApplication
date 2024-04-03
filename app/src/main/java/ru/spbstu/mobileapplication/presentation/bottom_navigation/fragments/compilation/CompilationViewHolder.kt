package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.compilation

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.spbstu.mobileapplication.R

class CompilationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.item_name)
    var city: TextView = view.findViewById(R.id.item_city)
    var image: ImageView = view.findViewById(R.id.imageViewMainBackground)
    var previousLayout: LinearLayout = view.findViewById(R.id.previousLayout)
    var nextLayout: LinearLayout = view.findViewById(R.id.nextLayout)
}