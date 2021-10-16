package com.efedaniel.ulesson.ulessonapp.screens.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.efedaniel.ulesson.R
import com.efedaniel.ulesson.base.BaseFragment
import com.efedaniel.ulesson.databinding.FragmentLiveBinding
import com.efedaniel.ulesson.extensions.observeNonNull
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson
import com.efedaniel.ulesson.ulessonapp.screens.live.pager.PromotedPagerAdapter
import javax.inject.Inject

class LiveFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LiveViewModel
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LiveViewModel::class.java)
        binding.viewModel = viewModel

        binding.fab.setOnClickListener {
            findNavController().navigate(LiveFragmentDirections.actionLiveFragmentToMeFragment())
        }

        binding.lessonRecyclerView.adapter = LiveLessonsAdapter()

        viewModel.promotedLessons.observeNonNull(viewLifecycleOwner, ::setupViewPager)
        viewModel.subjectList.observeNonNull(viewLifecycleOwner, ::setupSpinner)
    }

    private fun setupSpinner(subjects: List<String>) {
        val dataAdapter = ArrayAdapter(requireContext(), R.layout.layout_spinner_subjects, subjects)
        dataAdapter.setDropDownViewResource(R.layout.layout_spinner_subjects_dropdown)
        binding.subjectSpinner.adapter = dataAdapter
        binding.subjectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.onSubjectSelected(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setupViewPager(lessons: List<Lesson>) = binding.carousel.run {
        adapter = PromotedPagerAdapter(childFragmentManager, lessons)
        binding.dotsIndicator.setViewPager(this)
        setCycle(true)
        setAutoScrollDurationFactor(2.0)
        isStopScrollWhenTouch = true
        interval = 5000
        startAutoScroll()
        pageMargin = 20
        setPadding(20, 0, 20, 0)
    }
}
