package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.compilation

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.spbstu.mobileapplication.databinding.ItemSpotBinding

class CompilationViewHolder(private val binding: ItemSpotBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val name: TextView = binding.itemName
    var city: TextView = binding.itemCity
    var image: ImageView = binding.imageViewMainBackground
    var previousLayout: LinearLayout = binding.previousLayout
    var nextLayout: LinearLayout = binding.nextLayout
}