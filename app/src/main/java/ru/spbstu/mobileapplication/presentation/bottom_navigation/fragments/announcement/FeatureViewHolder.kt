package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.announcement

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.spbstu.mobileapplication.databinding.ItemFeatureBinding

class FeatureViewHolder(private val binding: ItemFeatureBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val featureName: TextView = binding.refrigeratorTextView
    val featureIcon: ImageView = binding.refrigeratorImageView
}