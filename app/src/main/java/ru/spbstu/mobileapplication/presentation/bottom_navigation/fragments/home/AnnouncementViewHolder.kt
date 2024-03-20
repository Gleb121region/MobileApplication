package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.ItemAnnouncementBinding
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity

class AnnouncementViewHolder(private val binding: ItemAnnouncementBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var dots: Array<ImageView>

    fun bind(announcement: AnnouncementEntity) {
        binding.announcementItem = announcement

        val viewPager = binding.root.findViewById<ViewPager>(R.id.view_pager_photos)
        val dotsLayout = binding.root.findViewById<LinearLayout>(R.id.dots_layout)

        val adapter = PhotoPagerAdapter(itemView.context, announcement.photoUrls)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                updateDots(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        addDots(announcement.photoUrls.size, dotsLayout)

        if (announcement.photoUrls.isNotEmpty()) {
            updateDots(0)
        }
    }

    private fun addDots(size: Int, dotsLayout: LinearLayout) {
        dots = Array(size) { ImageView(itemView.context) }
        for (i in dots.indices) {
            dots[i].setImageDrawable(
                ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.non_active_dot
                )
            )

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            dotsLayout.addView(dots[i], params)
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
