package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.compilation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity

class CardStackViewAdapter(private var announcements: List<AnnouncementEntity>) :
    RecyclerView.Adapter<CompilationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompilationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CompilationViewHolder(inflater.inflate(R.layout.item_spot, parent, false))
    }

    override fun onBindViewHolder(holder: CompilationViewHolder, position: Int) {
        val announcement = announcements[position]
        val size = announcement.photoUrls.size
        holder.name.text = announcement.underground
        holder.city.text = announcement.address
        Picasso.get().load(announcement.photoUrls[announcement.currentImagePosition])
            .into(holder.image)

        holder.previousLayout.setOnClickListener {
            Log.d(TAG, "PreviousLayout Listener")
            if (announcement.currentImagePosition > 0) {
                announcement.currentImagePosition--
                Picasso.get().load(announcement.photoUrls[announcement.currentImagePosition])
                    .into(holder.image)
            }
        }

        holder.nextLayout.setOnClickListener {
            Log.d(TAG, "NextLayout Listener")
            if (announcement.currentImagePosition < size - 1) {
                announcement.currentImagePosition++
                Picasso.get().load(announcement.photoUrls[announcement.currentImagePosition])
                    .into(holder.image)
            }
        }

        holder.itemView.setOnClickListener { v ->
            Toast.makeText(v.context, announcement.address, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return announcements.size
    }

    fun setSpots(spots: List<AnnouncementEntity>) {
        this.announcements = spots
    }

    fun getSpots(): List<AnnouncementEntity> {
        return announcements
    }

    private companion object {
        private const val TAG = "CardStackViewAdapter"
    }
}