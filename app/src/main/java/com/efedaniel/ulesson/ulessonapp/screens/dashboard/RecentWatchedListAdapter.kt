package com.efedaniel.ulesson.ulessonapp.screens.dashboard

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efedaniel.ulesson.R
import com.efedaniel.ulesson.databinding.ItemRecentlyWatchedBinding
import com.efedaniel.ulesson.extensions.inflate
import com.efedaniel.ulesson.ulessonapp.models.general.RecentlyWatched

class RecentWatchedListAdapter(
    private val listener: RecentlyWatchedListListener
) : ListAdapter<RecentlyWatched, RecentWatchedListAdapter.RecentlyWatchedViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<RecentlyWatched>() {
        override fun areItemsTheSame(oldItem: RecentlyWatched, newItem: RecentlyWatched): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecentlyWatched, newItem: RecentlyWatched): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentlyWatchedViewHolder {
        return RecentlyWatchedViewHolder(ItemRecentlyWatchedBinding.bind(parent.inflate(R.layout.item_recently_watched)))
    }

    override fun onBindViewHolder(holder: RecentlyWatchedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RecentlyWatchedViewHolder(
        private val binding: ItemRecentlyWatchedBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecentlyWatched) = binding.run {
            lesson = item
            rootLayout.setOnClickListener { listener.onRecentlyWatchedLessonClicked(item) }
            executePendingBindings()
        }
    }
}

interface RecentlyWatchedListListener {
    fun onRecentlyWatchedLessonClicked(lesson: RecentlyWatched)
}

@BindingAdapter("recentlyWatchedLessonList")
fun bindRecentlyWatchedRecyclerView(recyclerView: RecyclerView, data: List<RecentlyWatched>?) {
    data?.let { (recyclerView.adapter as RecentWatchedListAdapter).submitList(data) }
}
