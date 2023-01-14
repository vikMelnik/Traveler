package com.example.traveler.restaurants

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.traveler.databinding.FragmentRestaurantsItemBinding
import com.example.traveler.model.entities.listEntities.Restaurants
import com.example.traveler.restaurants.restaurantfragments.RestaurantListFragment

class RestaurantListAdapter(private val itemClickListener: RestaurantListFragment.OnItemViewClickListener)
	: RecyclerView.Adapter<RestaurantListAdapter.RestaurantHolder>() {

	private var listData: List<Restaurants> = listOf()
	private lateinit var binding: FragmentRestaurantsItemBinding

	@SuppressLint("NotifyDataSetChanged")
	fun setRestaurant(data: List<Restaurants>) {
		listData = data
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantHolder {
		val binding = FragmentRestaurantsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return RestaurantHolder(binding.root)
	}

	override fun onBindViewHolder(holder: RestaurantHolder, position: Int) {
		holder.bind(listData[position])
	}

	override fun getItemCount() = listData.size

	inner class RestaurantHolder(itemView: View): RecyclerView.ViewHolder(itemView){
		fun bind(restaurants: Restaurants){
			val binding = FragmentRestaurantsItemBinding.bind(itemView)
			binding.restaurantName.text = restaurants.name
			binding.rating.text = restaurants.rating.toString()
			binding.timeWork.text = restaurants.timeWork
			binding.signImageView.setImageResource(restaurants.picImgId)
			binding.root.setOnClickListener{itemClickListener.onItemViewClick(restaurants)}

		}

	}
}