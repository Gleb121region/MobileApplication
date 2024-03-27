package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.compilation

import androidx.recyclerview.widget.DiffUtil
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity

class AnnouncementDiffCallback(
    private val old: List<AnnouncementEntity>,
    private val new: List<AnnouncementEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition].id == new[newPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition] == new[newPosition]
    }

}
