package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.spbstu.mobileapplication.databinding.ItemAnnouncementBinding
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity

class AnnouncementAdapter(private val announcements: List<AnnouncementEntity>) :
    RecyclerView.Adapter<AnnouncementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAnnouncementBinding.inflate(inflater, parent, false)
        return AnnouncementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        holder.bind(announcements[position])
    }

    override fun getItemCount() = announcements.size
}
