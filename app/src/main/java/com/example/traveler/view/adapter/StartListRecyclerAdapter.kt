package com.example.traveler.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.traveler.R
import com.example.traveler.databinding.FragmentRecyclerItemHeaderBinding
import com.example.traveler.databinding.FragmentRecyclerItemStartGeneralBinding
import com.example.traveler.model.GeneralListTraveler
import com.example.traveler.model.TYPE_NAME

class StartListRecyclerAdapter(private val listData: ArrayList<GeneralListTraveler>)
	: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	override fun getItemViewType(position: Int): Int {
		return listData[position].type
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return when(viewType){
			TYPE_NAME -> {
				val binding = FragmentRecyclerItemStartGeneralBinding.inflate(LayoutInflater.from(parent.context))
				StartGeneralViewHolder(binding)
			}
			else ->{
				val binding = FragmentRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context))
				StartHeaderViewHolder(binding)
			}
		}
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		when(getItemViewType(position)){
			TYPE_NAME -> {
				(holder as StartGeneralViewHolder).bind(listData[position])
			}
			else ->{
				(holder as StartHeaderViewHolder).bind(listData[position])
			}
		}
	}

	override fun getItemCount(): Int {
		return listData.size
	}
}

class StartHeaderViewHolder(private val binding: FragmentRecyclerItemHeaderBinding)
	: RecyclerView.ViewHolder(binding.root) {
	fun bind(generalListTraveler: GeneralListTraveler) {
		binding.header.text = generalListTraveler.name

	}
}
class StartGeneralViewHolder(private val binding: FragmentRecyclerItemStartGeneralBinding)
	: RecyclerView.ViewHolder(binding.root){
		fun bind(generalListTraveler: GeneralListTraveler){
			binding.textName.text = generalListTraveler.name
			binding.signImageView.setImageResource(generalListTraveler.sign)
		}
	}

