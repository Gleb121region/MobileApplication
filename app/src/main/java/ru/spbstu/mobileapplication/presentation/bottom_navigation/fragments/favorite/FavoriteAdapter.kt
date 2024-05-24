package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import ru.spbstu.mobileapplication.databinding.ItemAnnouncementBinding
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnDefaultClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnDislikeClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnLikeClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnSkipClickListener

class FavoriteAdapter(
    val announcements: MutableList<AnnouncementEntity>,
    private val viewModel: FavoriteViewModel,
    private val skipClickListener: OnSkipClickListener?,
    private val dislikeClickListener: OnDislikeClickListener?,
    private val likeClickListener: OnLikeClickListener?,
    private val defaultClickListener: OnDefaultClickListener?,
) : PagingDataAdapter<AnnouncementEntity, FavoriteViewHolder>(COMPARATOR) {

    fun updateAnnouncement(position: Int, updatedAnnouncement: AnnouncementEntity) {
        announcements[position] = updatedAnnouncement
        notifyItemChanged(position)
    }

    fun deleteAnnouncement(position: Int) {
        announcements.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, announcements.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAnnouncementBinding.inflate(inflater, parent, false)
        return FavoriteViewHolder(
            binding,
            viewModel,
            skipClickListener,
            dislikeClickListener,
            likeClickListener,
            defaultClickListener
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(announcements[position], position, this)
    }

    fun handleImageNavigation(position: Int, isNext: Boolean) {
        val announcement = announcements[position]
        val size = announcement.photoUrls.size
        if (isNext && announcement.currentImagePosition < size - 1) {
            announcement.currentImagePosition++
        } else if (!isNext && announcement.currentImagePosition > 0) {
            announcement.currentImagePosition--
        }
        notifyItemChanged(position)
    }

    override fun getItemCount() = announcements.size

    private companion object {
        private const val TAG = "FavoriteAdapter"

        private val COMPARATOR = object : DiffUtil.ItemCallback<AnnouncementEntity>() {
            override fun areItemsTheSame(
                oldItem: AnnouncementEntity, newItem: AnnouncementEntity
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: AnnouncementEntity, newItem: AnnouncementEntity
            ): Boolean = oldItem == newItem
        }
    }
}
