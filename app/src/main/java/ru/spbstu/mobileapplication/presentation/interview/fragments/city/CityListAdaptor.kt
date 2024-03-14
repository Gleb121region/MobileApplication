package ru.spbstu.mobileapplication.presentation.interview.fragments.city


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.domain.enums.interview.City

class CityAdapter(
    private val cities: List<City>, private val onCityClickListener: OnCityClickListener
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    interface OnCityClickListener {
        fun onCityClick(city: City)
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityNameTextView: TextView = itemView.findViewById(R.id.cityNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.cityNameTextView.text = city.getCityName(holder.itemView.context)
        holder.itemView.setOnClickListener {
            onCityClickListener.onCityClick(city)
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }
}
