package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import ru.spbstu.mobileapplication.databinding.ItemAnnouncementBinding
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnDislikeClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnLikeClickListener

class FavoriteAdapter(
    val announcements: MutableList<AnnouncementEntity>,
    private val viewModel: FavoriteViewModel,
    private val dislikeClickListener: OnDislikeClickListener?,
    private val likeClickListener: OnLikeClickListener?,
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
            dislikeClickListener,
            likeClickListener
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(announcements[position], position)
    }

    override fun getItemCount() = announcements.size

    private companion object {
        private const val TAG = "FavoriteAdapter"

        private val COMPARATOR = object : DiffUtil.ItemCallback<AnnouncementEntity>() {
            override fun areItemsTheSame(
                oldItem: AnnouncementEntity,
                newItem: AnnouncementEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: AnnouncementEntity,
                newItem: AnnouncementEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

