package com.example.mytrainingsession

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mytrainingsession.databinding.ListItemBinding

class ExerciseAdapter(val onClick: (Int) -> Unit): ListAdapter<Exercise, ExerciseAdapter.ExerciseViewHolder>(DiffUtilCallback()) {
    class ExerciseViewHolder(private val binding: ListItemBinding, val onClick: (Int) -> Unit): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick(adapterPosition)
            }
        }

        fun bind(exercise: Exercise) {
            binding.apply {
                gifGIV.setImageResource(exercise.gifResourceId)
                nameTV.text = exercise.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ExerciseViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffUtilCallback: DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem // because its data class
        }
    }
}