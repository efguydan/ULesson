package com.efedaniel.ulesson.ulessonapp.screens.live

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efedaniel.ulesson.R
import com.efedaniel.ulesson.databinding.ItemLiveLessonBinding
import com.efedaniel.ulesson.extensions.inflate
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson

class LiveLessonsAdapter() : ListAdapter<Lesson, LiveLessonsAdapter.ViewHolder>(LessonDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLiveLessonBinding.bind(parent.inflate(R.layout.item_live_lesson)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemLiveLessonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Lesson) = binding.run {
            lesson = item
            executePendingBindings()
        }
    }
}

object LessonDiffCallback : DiffUtil.ItemCallback<Lesson>() {
    override fun areItemsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
        return oldItem == newItem
    }
}

@BindingAdapter("liveLessonList")
fun bindLiveLessonRecyclerView(recyclerView: RecyclerView, data: List<Lesson>?) {
    data?.let { (recyclerView.adapter as LiveLessonsAdapter).submitList(data) }
}
