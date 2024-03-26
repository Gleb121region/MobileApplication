package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso
import ru.spbstu.mobileapplication.R

class PhotoPagerAdapter(private val context: Context, private val photoUrls: List<String>) :
    PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_photo, container, false)

        val imageView = view.findViewById<ImageView>(R.id.photoImageView)
        Picasso.get().load(photoUrls[position]).into(imageView)

        container.addView(view)
        return view
    }

    override fun getCount(): Int {
        return photoUrls.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}
