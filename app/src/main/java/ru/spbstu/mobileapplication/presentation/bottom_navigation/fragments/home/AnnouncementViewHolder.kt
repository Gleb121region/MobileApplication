package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.ItemAnnouncementBinding
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnDefaultClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnDislikeClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnLikeClickListener

class AnnouncementViewHolder(
    binding: ItemAnnouncementBinding,
    private val viewModel: HomeViewModel,
    private val dislikeClickListener: OnDislikeClickListener?,
    private val likeClickListener: OnLikeClickListener?,
    private val defaultClickListener: OnDefaultClickListener?
) : RecyclerView.ViewHolder(binding.root) {

    private val cardStackView: CardView = binding.cardStackView

    private val price: TextView = binding.itemPrice
    private val type: TextView = binding.itemType
    private val square: TextView = binding.itemSquare
    private val storey: TextView = binding.itemStorey
    private val underground: TextView = binding.itemUnderground
    private val address: TextView = binding.itemAddress

    private val image: ImageView = binding.imageViewMainBackground
    private val imageLike: ImageView = binding.ivLike
    private val imageDislike: ImageView = binding.ivDislike

    private val previousLayout: LinearLayout = binding.previousLayout
    private val nextLayout: LinearLayout = binding.nextLayout

    fun bind(announcement: AnnouncementEntity, position: Int, adapter: AnnouncementAdapter) {
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

        if (announcement.isLikedByUser) {
            imageLike.setImageResource(R.drawable.like_green_24dp)
        } else {
            imageLike.setImageResource(R.drawable.like_gray_24dp)
        }

        imageLike.setOnClickListener {
            if (announcement.isLikedByUser) {
                defaultClickListener?.onItemDefault(position)
            } else {
                likeClickListener?.onItemLike(position)
            }
        }

        imageDislike.setOnClickListener {
            dislikeClickListener?.onItemDislike(position)
        }

        cardStackView.setOnClickListener {
            viewModel.selectedAnnouncementId.value = announcement.id
        }
    }
}
