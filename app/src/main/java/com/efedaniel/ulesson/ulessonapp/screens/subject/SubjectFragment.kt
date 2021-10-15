package com.efedaniel.ulesson.ulessonapp.screens.subject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.efedaniel.ulesson.base.BaseFragment
import com.efedaniel.ulesson.databinding.FragmentSubjectBinding
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson
import javax.inject.Inject

class SubjectFragment : BaseFragment(), LessonListListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SubjectViewModel
    private lateinit var binding: FragmentSubjectBinding
    private val args: SubjectFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubjectBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SubjectViewModel::class.java)
        binding.viewModel = viewModel

        binding.run {
            subjectNameTextView.text = args.subject.name
            backButton.setOnClickListener { mainActivity.onBackPressed() }
            chapterRecycler.adapter = ChapterListAdapter(this@SubjectFragment)
        }

        viewModel.getSubjectChapters(args.subject)
    }

    override fun onLessonClicked(lesson: Lesson) {
        viewModel.insertRecentlyWatched(lesson, args.subject)
        findNavController().navigate(SubjectFragmentDirections.actionSubjectFragmentToLessonFragment(lesson))
    }
}
