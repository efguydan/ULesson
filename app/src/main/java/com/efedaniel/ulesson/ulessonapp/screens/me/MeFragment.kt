package com.efedaniel.ulesson.ulessonapp.screens.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.efedaniel.ulesson.R
import com.efedaniel.ulesson.base.BaseFragment
import com.efedaniel.ulesson.databinding.FragmentMeBinding
import com.efedaniel.ulesson.extensions.invalidateElevation
import com.efedaniel.ulesson.extensions.observeNonNull
import com.efedaniel.ulesson.extensions.onScrollChanged
import com.efedaniel.ulesson.extensions.onSelectedIndexChanged
import javax.inject.Inject

class MeFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MeViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentMeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        binding.viewModel = viewModel

        binding.lessonRecyclerView.adapter = MyLessonsAdapter()

        viewModel.subjectList.observeNonNull(viewLifecycleOwner, ::setupSpinner)

        binding.scrollView.onScrollChanged { binding.toolbarLayout.invalidateElevation(it) }
    }

    private fun setupSpinner(subjects: List<String>) {
        val dataAdapter = ArrayAdapter(requireContext(), R.layout.layout_spinner_subjects, subjects)
        dataAdapter.setDropDownViewResource(R.layout.layout_spinner_subjects_dropdown)
        binding.subjectSpinner.adapter = dataAdapter
        binding.subjectSpinner.onSelectedIndexChanged(viewModel::onSubjectSelected)
    }
}
