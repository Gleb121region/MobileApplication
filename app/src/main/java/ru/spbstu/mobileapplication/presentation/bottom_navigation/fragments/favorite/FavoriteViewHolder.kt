package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.favorite

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.ItemAnnouncementBinding
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.PhotoPagerAdapter
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnDislikeClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnLikeClickListener

class FavoriteViewHolder(
    private val binding: ItemAnnouncementBinding,
    private val dislikeClickListener: OnDislikeClickListener?,
    private val likeClickListener: OnLikeClickListener?,
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var dots: Array<ImageView>

    init {
        dots = Array(0) { ImageView(itemView.context) }
    }

    fun bind(announcement: AnnouncementEntity, position: Int) {
        binding.announcementItem = announcement
        binding.executePendingBindings()

        val adapter = PhotoPagerAdapter(itemView.context, announcement.photoUrls)
        binding.viewPagerPhotos.adapter = adapter

        binding.viewPagerPhotos.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int, positionOffset: Float, positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                updateDots(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        updateDotsCount(announcement.photoUrls.size, binding.dotsLayout)

        if (announcement.photoUrls.isNotEmpty()) {
            updateDots(0)
        }

        binding.ivLike.setImageResource(R.drawable.like_green_24dp)

        binding.ivLike.setOnClickListener {
            likeClickListener?.onItemLike(position)
        }

        binding.ivDislike.setOnClickListener {
            dislikeClickListener?.onItemDislike(position)
        }

    }

    private fun updateDotsCount(size: Int, dotsLayout: LinearLayout) {
        dotsLayout.removeAllViews()

        dots = Array(size) {
            val dot = ImageView(itemView.context)
            dot.setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context, R.drawable.non_active_dot
                )
            )

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            dotsLayout.addView(dot, params)
            dot
        }
    }

    private fun updateDots(currentDot: Int) {
        for (i in dots.indices) {
            val drawable: Drawable? = if (i == currentDot) {
                ContextCompat.getDrawable(itemView.context, R.drawable.active_dot)
            } else {
                ContextCompat.getDrawable(itemView.context, R.drawable.non_active_dot)
            }
            dots[i].setImageDrawable(drawable)
        }
    }
}
