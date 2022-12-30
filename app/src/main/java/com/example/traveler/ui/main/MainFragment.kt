package com.example.traveler.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.traveler.databinding.FragmentMainBinding
import com.example.traveler.viewmodel.MainViewModel

class MainFragment : Fragment() {

	private lateinit var binding: FragmentMainBinding

	companion object {
		fun newInstance() = MainFragment()
	}

	private lateinit var viewModel: MainViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewModel = ViewModelProvider(this)[MainViewModel::class.java]
		// TODO: Use the ViewModel
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentMainBinding.inflate(inflater, container,false)
		return binding.root
	}

}