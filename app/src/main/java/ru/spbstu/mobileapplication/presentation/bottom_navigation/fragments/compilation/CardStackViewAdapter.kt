package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.compilation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.spbstu.mobileapplication.databinding.ItemSpotBinding
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.HomeViewModel

class CardStackViewAdapter(
    var announcements: MutableList<AnnouncementEntity>,
    private val viewModel: CompilationViewModel,
) : RecyclerView.Adapter<CompilationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompilationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CompilationViewHolder(ItemSpotBinding.inflate(inflater, parent, false), viewModel)
    }

    override fun onBindViewHolder(holder: CompilationViewHolder, position: Int) {
        holder.bind(announcements[position], position, this)
    }

    override fun getItemCount(): Int {
        return announcements.size
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

    private companion object {
        private const val TAG = "CardStackViewAdapter"
    }
}
