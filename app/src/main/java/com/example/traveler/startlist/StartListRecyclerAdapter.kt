package com.example.traveler.startlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.traveler.databinding.FragmentRecyclerItemHeaderBinding
import com.example.traveler.databinding.FragmentRecyclerItemStartGeneralBinding
import com.example.traveler.model.entities.GeneralListTraveler
import com.example.traveler.model.entities.TYPE_NAME

class StartListRecyclerAdapter(
	private val listData: ArrayList<GeneralListTraveler>,
	private val itemClickListener: StartListFragment.OnItemViewClickListener
) :
	RecyclerView.Adapter<BaseViewHolder>() {


	override fun getItemViewType(position: Int): Int {
		return listData[position].type
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
		return when (viewType) {
			TYPE_NAME -> {
				val binding =
					FragmentRecyclerItemStartGeneralBinding.inflate(LayoutInflater.from(parent.context))
				StartGeneralViewHolder(binding)
			}
			else -> {
				val binding =
					FragmentRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context))
				StartHeaderViewHolder(binding)
			}
		}
	}

	override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
		holder.bind(listData[position])
	}

	override fun getItemCount(): Int {
		return listData.size
	}


	class StartHeaderViewHolder(private val binding: FragmentRecyclerItemHeaderBinding) :
		BaseViewHolder(binding.root) {
		override fun bind(generalListTraveler: GeneralListTraveler) {
			binding.header.text = generalListTraveler.name

		}
	}

	inner class StartGeneralViewHolder(private val binding: FragmentRecyclerItemStartGeneralBinding) :
		BaseViewHolder(binding.root) {
		override fun bind(generalListTraveler: GeneralListTraveler) {
			binding.textName.text = generalListTraveler.name
			binding.signImageView.setImageResource(generalListTraveler.sign)
			binding.root.setOnClickListener {
				itemClickListener.onItemViewClick(generalListTraveler)
			}
		}
	}
}

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
	abstract fun bind(generalListTraveler: GeneralListTraveler)
}

