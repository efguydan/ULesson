package com.efedaniel.ulesson.ulessonapp.screens.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.efedaniel.ulesson.R
import com.efedaniel.ulesson.base.BaseFragment
import com.efedaniel.ulesson.databinding.FragmentDashboardBinding
import com.efedaniel.ulesson.extensions.hide
import com.efedaniel.ulesson.extensions.observeNonNull
import com.efedaniel.ulesson.extensions.show
import com.efedaniel.ulesson.networkutils.LoadingStatus
import com.efedaniel.ulesson.ulessonapp.models.general.RecentlyWatched
import com.efedaniel.ulesson.ulessonapp.models.general.Subject
import com.efedaniel.ulesson.ulessonapp.models.general.toLesson
import com.efedaniel.ulesson.ulessonapp.models.general.toLocalRecentlyWatched
import javax.inject.Inject

class DashboardFragment : BaseFragment(), SubjectListListener, RecentlyWatchedListListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)
        viewModel.getSubjects()
        binding.viewModel = viewModel

        binding.subjectsRecyclerView.adapter = SubjectListAdapter(this)
        binding.retryButton.setOnClickListener { viewModel.getSubjects(true) }
        binding.recentlyWatchedRecycler.adapter = RecentWatchedListAdapter(this)
        binding.viewAllButton.setOnClickListener {
            viewModel.toggleRecentlyWatchedLimit()
        }

        observeLiveData()
    }

    override fun onSubjectClicked(subject: Subject) {
        findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToSubjectFragment(subject))
    }

    override fun onRecentlyWatchedLessonClicked(recentlyWatched: RecentlyWatched) {
        viewModel.insertRecentlyWatched(recentlyWatched.toLocalRecentlyWatched())
        findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToLessonFragment(recentlyWatched.toLesson()))
    }

    private fun observeLiveData() {
        viewModel.loadingStatus.observeNonNull(
            this,
            {
                when (it) {
                    is LoadingStatus.Loading -> {
                        binding.loadingFrameLayout.show()
                        binding.progressBar.show()
                        binding.retryButton.hide()
                    }
                    is LoadingStatus.Success -> binding.loadingFrameLayout.hide()
                    is LoadingStatus.Error -> {
                        binding.loadingFrameLayout.show()
                        binding.retryButton.show()
                        binding.progressBar.hide()
                    }
                }
            }
        )
        viewModel.itemCount.observeNonNull(this) {
            binding.viewAllButton.text = if (it == 2) getString(R.string.view_all) else getString(R.string.see_less)
        }
    }
}
