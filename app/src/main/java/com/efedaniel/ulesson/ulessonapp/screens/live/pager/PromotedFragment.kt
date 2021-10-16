package com.efedaniel.ulesson.ulessonapp.screens.live.pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.efedaniel.ulesson.R
import com.efedaniel.ulesson.databinding.FragmentPromotedBinding
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson

class PromotedFragment : Fragment() {

    companion object {
        const val LESSON_KEY = "Lesson Argument"

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
        inflater: LayoutInflater, container: ViewGroup?,
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
        }
    }

}