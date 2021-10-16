package com.efedaniel.ulesson.ulessonapp.screens.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.efedaniel.ulesson.base.BaseFragment
import com.efedaniel.ulesson.databinding.FragmentMeBinding
import javax.inject.Inject

class MeFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MeViewModel
    private lateinit var binding: FragmentMeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MeViewModel::class.java)
    }

}