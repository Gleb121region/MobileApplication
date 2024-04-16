package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.compilation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.spbstu.mobileapplication.databinding.ItemSpotBinding
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity

class CardStackViewAdapter(
    var announcements: MutableList<AnnouncementEntity>
) : RecyclerView.Adapter<CompilationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompilationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CompilationViewHolder(ItemSpotBinding.inflate(inflater, parent, false))
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
                notifyItemChanged(holder.layoutPosition)
            }
        }

        holder.nextLayout.setOnClickListener {
            Log.d(TAG, "NextLayout Listener")
            if (announcement.currentImagePosition < size - 1) {
                announcement.currentImagePosition++
                notifyItemChanged(holder.layoutPosition)
            }
        }

        holder.itemView.setOnClickListener { v ->
            Toast.makeText(v.context, announcement.address, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return announcements.size
    }

    private companion object {
        private const val TAG = "CardStackViewAdapter"
    }
}