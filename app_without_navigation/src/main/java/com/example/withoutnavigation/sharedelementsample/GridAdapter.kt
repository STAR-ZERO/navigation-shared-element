package com.example.withoutnavigation.sharedelementsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.withoutnavigation.sharedelementsample.databinding.ItemImageBinding

class GridAdapter(
        private val clickItem: (View) -> Unit
) : ListAdapter<String, GridAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickItem)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageURL: String, clickItem: (View) -> Unit) {
            binding.image.transitionName = imageURL // Use image url for transitionName
            binding.imageURL = imageURL
            binding.image.setOnClickListener {
                clickItem(binding.image)
            }
            binding.executePendingBindings()
        }
    }
}
