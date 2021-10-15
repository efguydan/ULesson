package com.efedaniel.ulesson.ulessonapp.screens.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.efedaniel.ulesson.R
import com.efedaniel.ulesson.base.BaseFragment
import com.efedaniel.ulesson.databinding.FragmentLessonBinding
import com.efedaniel.ulesson.extensions.isLandScape
import com.efedaniel.ulesson.extensions.makeFullScreen
import com.efedaniel.ulesson.extensions.makeStatusBarTransparent
import com.efedaniel.ulesson.extensions.observeNonNull
import javax.inject.Inject

class LessonFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LessonViewModel
    private lateinit var binding: FragmentLessonBinding
    private val args: LessonFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLessonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LessonViewModel::class.java)
        setupScreen()
    }

    override fun onStart() {
        super.onStart()
        viewModel.videoPlayer.initializePlayer(requireContext(), args.lesson.videoLink)
        with(mainActivity) {
            if (isLandScape()) makeFullScreen()
            else makeStatusBarTransparent()
        }
    }

    override fun onStop() {
        if (!mainActivity.isChangingConfigurations) {
            viewModel.videoPlayer.releasePlayer()
            mainActivity.makeStatusBarTransparent()
        }
        super.onStop()
    }

    private fun setupScreen() {
        view?.findViewById<ImageView>(R.id.back_button)?.setOnClickListener { mainActivity.onBackPressed() }
        viewModel.exoPlayer.observeNonNull(this, { binding.exoPlayerView.player = it })
        binding.lessonTitle?.text = args.lesson.name
    }
}
