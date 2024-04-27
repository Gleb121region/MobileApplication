package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.announcement

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.ItemFeatureBinding

class FeaturesAdapter(var features: List<String>) : RecyclerView.Adapter<FeatureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = FeatureViewHolder(ItemFeatureBinding.inflate(inflater, parent, false))
        Log.d(TAG, "onCreateViewHolder: ViewHolder created")
        return viewHolder
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        val feature = features[position]
        Log.d(TAG, "onBindViewHolder: Binding feature at position $position: $feature")
        holder.featureName.text = feature
        when (feature) {
            "Холодильник" -> holder.featureIcon.setImageResource(R.drawable.ic_fridge_black_24dp)
            "Стиральная машина" -> holder.featureIcon.setImageResource(R.drawable.ic_washing_machine_black_24dp)
            "Телевизор" -> holder.featureIcon.setImageResource(R.drawable.ic_television_black_24dp)
            "Душевая кабина" -> holder.featureIcon.setImageResource(R.drawable.ic_shower_black_24dp)
            "Ванна" -> holder.featureIcon.setImageResource(R.drawable.ic_bath_black_24dp)
            "Мебель в квартире" -> holder.featureIcon.setImageResource(R.drawable.ic_furniturer_black_24dp)
            "Мебель на кухн" -> holder.featureIcon.setImageResource(R.drawable.ic_furniture_kitchen_black_24dp)
            "Посудомоечная машина" -> holder.featureIcon.setImageResource(R.drawable.ic_dishwasher_black_24dp)
            "Кондиционер" -> holder.featureIcon.setImageResource(R.drawable.ic_air_conditioner_black_24dp)
            "Интернет" -> holder.featureIcon.setImageResource(R.drawable.ic_internet_black_24dp)
        }
    }

    override fun getItemCount() = features.size

    companion object {
        const val TAG = "FeaturesAdapter"
    }
}

