package com.efedaniel.ulesson.ulessonapp.screens.live.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.efedaniel.ulesson.databinding.FragmentPromotedBinding
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson
import com.efedaniel.ulesson.ulessonapp.screens.live.LiveFragment

class PromotedFragment : Fragment() {

    companion object {
        const val LESSON_KEY = "lesson_argument"

        @JvmStatic
        fun newInstance(lesson: Lesson) =
            PromotedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(LESSON_KEY, lesson)
                }
            }
    }

    private lateinit var binding: FragmentPromotedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPromotedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Lesson>(LESSON_KEY)?.let {
            binding.lesson = it
            binding.executePendingBindings()
            binding.rootView.setOnClickListener { _ ->
                (parentFragment as? LiveFragment)?.showSnackBar(it.topicName)
            }
        }
    }
}
