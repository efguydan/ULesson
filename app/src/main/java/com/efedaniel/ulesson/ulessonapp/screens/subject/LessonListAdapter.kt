package com.efedaniel.ulesson.ulessonapp.screens.subject

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efedaniel.ulesson.R
import com.efedaniel.ulesson.databinding.ItemLessonBinding
import com.efedaniel.ulesson.extensions.inflate
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson

class LessonListAdapter(
    private val listener: LessonListListener
) : ListAdapter<Lesson, LessonListAdapter.LessonViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Lesson>() {

        override fun areItemsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(ItemLessonBinding.bind(parent.inflate(R.layout.item_lesson)))
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LessonViewHolder(
        private val binding: ItemLessonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: Lesson) = binding.run {
            this.lesson = lesson
            executePendingBindings()
            rootCardView.setOnClickListener { listener.onLessonClicked(lesson) }
        }
    }
}

interface LessonListListener : ChapterListListener {
    fun onLessonClicked(lesson: Lesson)
}

@BindingAdapter("lessonList")
fun bindLessonRecyclerView(recyclerView: RecyclerView, data: List<Lesson>?) {
    data?.let { (recyclerView.adapter as LessonListAdapter).submitList(data) }
}
