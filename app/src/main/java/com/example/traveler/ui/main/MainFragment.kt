package com.example.traveler.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.traveler.databinding.FragmentMainBinding
import com.example.traveler.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

	private lateinit var binding: FragmentMainBinding

	companion object {
		fun newInstance() = MainFragment()
	}
	private val viewModel: MainViewModel by viewModel()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentMainBinding.inflate(inflater, container,false)
		return binding.root
	}

}