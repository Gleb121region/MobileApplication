package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.compilation

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.spbstu.mobileapplication.databinding.ItemSpotBinding
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity

class CompilationViewHolder(binding: ItemSpotBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val price: TextView = binding.itemPrice
    private val type: TextView = binding.itemType
    private val square: TextView = binding.itemSquare
    private val storey: TextView = binding.itemStorey
    private val underground: TextView = binding.itemUnderground
    private val address: TextView = binding.itemAddress
    private val image: ImageView = binding.imageViewMainBackground
    private val previousLayout: LinearLayout = binding.previousLayout
    private val nextLayout: LinearLayout = binding.nextLayout

    fun bind(announcement: AnnouncementEntity, position: Int, adapter: CardStackViewAdapter) {
        price.text = announcement.getFormattedPricePerMonth()
        type.text = announcement.getApartmentTypeRusName(itemView.context)
        square.text = announcement.getFormattedTotalMeters()
        storey.text = announcement.getFormattedFloorAndFloorsCount()
        underground.text = announcement.underground
        address.text = announcement.address

        Picasso.get().load(announcement.photoUrls[announcement.currentImagePosition])
            .into(image)

        previousLayout.setOnClickListener {
            adapter.handleImageNavigation(position, false)
        }

        nextLayout.setOnClickListener {
            adapter.handleImageNavigation(position, true)
        }
    }
}
