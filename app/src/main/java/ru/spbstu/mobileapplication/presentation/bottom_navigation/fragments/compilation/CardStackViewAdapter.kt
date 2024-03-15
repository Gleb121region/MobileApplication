//package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.compilation
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import ru.spbstu.mobileapplication.R
//
//class CardStackViewAdapter(
//    private val cardStackList: List<MovieDto>,
//    private val fragment: Fragment
//) : RecyclerView.Adapter<CardStackViewAdapter.CardStackViewHolder>() {
//
//    class CardStackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val image: ImageView = itemView.findViewById(R.id.cardViewImage)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardStackViewHolder {
//        val itemView =
//            LayoutInflater.from(parent.context).inflate(R.layout.card_stack_item, parent, false)
//        return CardStackViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: CardStackViewHolder, position: Int) {
//        Glide.with(fragment).load(cardStackList[position].poster).into(holder.image)
//
//    }
//
//    override fun getItemCount(): Int {
//        return cardStackList.size
//    }
//}