package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home

import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.ItemAnnouncementBinding
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnDefaultClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnDislikeClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnLikeClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnSkipClickListener

class AnnouncementViewHolder(
    binding: ItemAnnouncementBinding,
    private val viewModel: HomeViewModel,
    private val skipClickListener: OnSkipClickListener?,
    private val dislikeClickListener: OnDislikeClickListener?,
    private val likeClickListener: OnLikeClickListener?,
    private val defaultClickListener: OnDefaultClickListener?
) : RecyclerView.ViewHolder(binding.root) {

    private val price: TextView = binding.itemPrice
    private val type: TextView = binding.itemType
    private val square: TextView = binding.itemSquare
    private val storey: TextView = binding.itemStorey
    private val underground: TextView = binding.itemUnderground
    private val address: TextView = binding.itemAddress

    private val image: ImageView = binding.imageViewMainBackground
    private val imageLike: ImageView = binding.ivLike
    private val imageDots: ImageView = binding.ivDots

    private val infoIcon: ImageButton = binding.infoIcon

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

        imageLike.setImageResource(
            when {
                announcement.isLikedByUser -> R.drawable.ic_favorite_red_24dp
                else -> R.drawable.ic_favorite_default_24dp
            }
        )

        imageLike.setOnClickListener {
            when {
                announcement.isLikedByUser -> defaultClickListener?.onItemDefault(position)
                else -> likeClickListener?.onItemLike(position)
            }
        }

        imageDots.setOnClickListener { view ->
            val popupMenu = PopupMenu(view.context, view)
            popupMenu.menu.add(R.string.hide_ad)
            popupMenu.menu.add(R.string.do_not_show_ad)
            popupMenu.menu.add(R.string.call)
            popupMenu.setOnMenuItemClickListener {
                when (it.title) {
                    "Скрыть объявление" -> {
                        skipClickListener?.onItemSkip(position)
                    }

                    "Не показывать объявление" -> {
                        dislikeClickListener?.onItemDislike(position)
                    }

                    "Позвонить" -> {
                        viewModel.sendCallIntent(announcement.phoneNumber)
                    }

                    "Написать сообщение" -> {

                    }
                }
                true
            }
            popupMenu.show()
        }

        infoIcon.setOnClickListener {
            viewModel.selectedAnnouncementId.value = announcement.id
        }
    }
}
