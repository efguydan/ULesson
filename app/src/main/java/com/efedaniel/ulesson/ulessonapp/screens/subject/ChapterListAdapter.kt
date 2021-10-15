package com.efedaniel.ulesson.ulessonapp.screens.subject

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efedaniel.ulesson.R
import com.efedaniel.ulesson.databinding.ItemChapterBinding
import com.efedaniel.ulesson.extensions.inflate
import com.efedaniel.ulesson.ulessonapp.models.general.Chapter

class ChapterListAdapter(
    private val listener: LessonListListener
) : ListAdapter<Chapter, ChapterListAdapter.ChapterViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Chapter>() {

        override fun areItemsTheSame(oldItem: Chapter, newItem: Chapter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Chapter, newItem: Chapter): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        return ChapterViewHolder(ItemChapterBinding.bind(parent.inflate(R.layout.item_chapter)))
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ChapterViewHolder(
        private val binding: ItemChapterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(chapter: Chapter) = binding.run {
            this.chapter = chapter
            lessonRecycler.adapter = LessonListAdapter(listener)
            executePendingBindings()
        }
    }
}

interface ChapterListListener

@BindingAdapter("chapterList")
fun bindChapterRecyclerView(recyclerView: RecyclerView, data: List<Chapter>?) {
    data?.let { (recyclerView.adapter as ChapterListAdapter).submitList(data) }
}
