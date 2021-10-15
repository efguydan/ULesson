package com.efedaniel.ulesson.ulessonapp.screens.dashboard

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efedaniel.ulesson.R
import com.efedaniel.ulesson.databinding.ItemSubjectBinding
import com.efedaniel.ulesson.extensions.inflate
import com.efedaniel.ulesson.ulessonapp.models.general.Subject

class SubjectListAdapter(
    private val listener: SubjectListListener
) : ListAdapter<Subject, SubjectListAdapter.SubjectViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Subject>() {
        override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        return SubjectViewHolder(ItemSubjectBinding.bind(parent.inflate(R.layout.item_subject)))
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SubjectViewHolder(
        private val binding: ItemSubjectBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(subject: Subject) = binding.run {
            this.subject = subject
            executePendingBindings()
            rootCardView.setOnClickListener { listener.onSubjectClicked(subject) }
        }
    }
}

interface SubjectListListener {
    fun onSubjectClicked(subject: Subject)
}

@BindingAdapter("subjectList")
fun bindSubjectRecyclerView(recyclerView: RecyclerView, data: List<Subject>?) {
    data?.let { (recyclerView.adapter as SubjectListAdapter).submitList(data) }
}
