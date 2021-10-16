package com.efedaniel.ulesson.ulessonapp.screens.me


import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efedaniel.ulesson.R
import com.efedaniel.ulesson.databinding.ItemMeLessonBinding
import com.efedaniel.ulesson.extensions.inflate
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson
import com.efedaniel.ulesson.ulessonapp.screens.live.LessonDiffCallback

class MyLessonsAdapter(

): ListAdapter<Lesson, MyLessonsAdapter.ViewHolder>(LessonDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMeLessonBinding.bind(parent.inflate(R.layout.item_me_lesson)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemMeLessonBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Lesson) = binding.run {
            lesson = item
            executePendingBindings()
        }

    }

}

@BindingAdapter("myLessonList")
fun bindMyLessonRecyclerView(recyclerView: RecyclerView, data: List<Lesson>?) {
    data?.let { (recyclerView.adapter as MyLessonsAdapter).submitList(data) }
}