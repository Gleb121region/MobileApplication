package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home

import androidx.recyclerview.widget.RecyclerView
import ru.spbstu.mobileapplication.databinding.ItemAnnouncementBinding
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity

class AnnouncementViewHolder(private val binding: ItemAnnouncementBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(announcement: AnnouncementEntity) {
        binding.announcementItem = announcement
        binding.executePendingBindings()
    }
}