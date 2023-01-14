package com.example.traveler.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.traveler.ui.view.StartListFragment
import com.example.traveler.welcomescreen.WelcomeScreenFragment

abstract class ViewBindingFragment<T : ViewBinding>(
	private val inflateBinding: (
		inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean
	) -> T
) : Fragment() {

	private var _binding: T? = null

	protected val binding: T
		get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = inflateBinding.invoke(inflater, container, false)
		return binding.root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	companion object {
		fun newInstance() = WelcomeScreenFragment()	}
}