package com.efedaniel.ulesson.ulessonapp.screens.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.efedaniel.ulesson.R
import com.efedaniel.ulesson.base.BaseFragment
import com.efedaniel.ulesson.databinding.FragmentLiveBinding
import com.efedaniel.ulesson.extensions.hide
import com.efedaniel.ulesson.extensions.invalidateElevation
import com.efedaniel.ulesson.extensions.observeNonNull
import com.efedaniel.ulesson.extensions.onScrollChanged
import com.efedaniel.ulesson.extensions.onSelectedIndexChanged
import com.efedaniel.ulesson.extensions.setupDefaultState
import com.efedaniel.ulesson.extensions.show
import com.efedaniel.ulesson.networkutils.LoadingStatus
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson
import com.efedaniel.ulesson.ulessonapp.screens.live.pager.PromotedPagerAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class LiveFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: LiveViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentLiveBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLiveBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        binding.viewModel = viewModel

        binding.fab.setOnClickListener {
            findNavController().navigate(LiveFragmentDirections.actionLiveFragmentToMeFragment())
        }

        binding.lessonRecyclerView.adapter = LiveLessonsAdapter(::showSnackBar)

        viewModel.promotedLessons.observeNonNull(viewLifecycleOwner, ::setupViewPager)
        viewModel.subjectList.observeNonNull(viewLifecycleOwner, ::setupSpinner)
        viewModel.loadingStatus.observeNonNull(viewLifecycleOwner, ::onLoadingStatusUpdated)

        binding.scrollView.onScrollChanged { binding.toolbarLayout.invalidateElevation(it) }
    }

    private fun setupSpinner(subjects: List<String>) {
        val dataAdapter = ArrayAdapter(requireContext(), R.layout.layout_spinner_subjects, subjects)
        dataAdapter.setDropDownViewResource(R.layout.layout_spinner_subjects_dropdown)
        binding.subjectSpinner.adapter = dataAdapter
        binding.subjectSpinner.onSelectedIndexChanged(viewModel::onSubjectSelected)
    }

    private fun setupViewPager(lessons: List<Lesson>) = binding.carousel.run {
        adapter = PromotedPagerAdapter(childFragmentManager, lessons)
        binding.dotsIndicator.setViewPager(this)
        setupDefaultState()
        startAutoScroll()
    }

    private fun onLoadingStatusUpdated(loadingStatus: LoadingStatus) {
        when(loadingStatus) {
            is LoadingStatus.Loading -> binding.loadingContainer.rootView.show()
            else -> binding.loadingContainer.rootView.hide()
        }
    }

    fun showSnackBar(
        message: String
    ) {
        Snackbar
            .make(binding.coordinatorLayout, message, Snackbar.LENGTH_LONG)
            .apply {
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.text_color))
            }
            .show()
    }

}
