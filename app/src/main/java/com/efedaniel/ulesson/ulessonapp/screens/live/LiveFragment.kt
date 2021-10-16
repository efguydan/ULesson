package com.efedaniel.ulesson.ulessonapp.screens.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.efedaniel.ulesson.base.BaseFragment
import com.efedaniel.ulesson.databinding.FragmentLiveBinding
import javax.inject.Inject

class LiveFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LiveViewModel
    private lateinit var binding: FragmentLiveBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLiveBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LiveViewModel::class.java)

        binding.fab.setOnClickListener {
            findNavController().navigate(LiveFragmentDirections.actionLiveFragmentToMeFragment())
        }
    }

}