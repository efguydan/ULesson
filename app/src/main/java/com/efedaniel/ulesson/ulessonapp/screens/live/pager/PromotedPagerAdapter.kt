package com.efedaniel.ulesson.ulessonapp.screens.live.pager

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson

class PromotedPagerAdapter(
    fragmentManager: FragmentManager,
    private val lessons: List<Lesson>
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount() = lessons.size

    override fun getItem(position: Int) = PromotedFragment.newInstance(lessons[position])

}