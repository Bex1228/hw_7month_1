package com.example.hw_7month_1.doors.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw_7month_1.databinding.ItemDoorsBinding
import com.example.hw_7month_1.response.DoorsModel

class DoorsAdapter: ListAdapter<DoorsModel.Data, DoorsViewHolder>(
    DoorDiffCallback()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DoorsViewHolder {
        return DoorsViewHolder(
            ItemDoorsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DoorsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


class DoorsViewHolder(private val binding: ItemDoorsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isOpened = false

        init {
            itemView.setOnClickListener {
                toggleDoor()
            }
        }

        fun bind(position: DoorsModel.Data) {
            binding.imgDoor.visibility = if (isOpened) VISIBLE else GONE
            binding.tvNameDoor.text = position.name
            Glide.with(binding.imgDoor).load(position.snapshot).into(binding.imgDoor)
        }

        private fun toggleDoor()= with(binding) {
            isOpened = !isOpened
            val translationY = if (isOpened) 0f else imgDoor.height.toFloat()

            imgDoor.animate()
                .translationY(translationY)
                .setDuration(500)
                .withEndAction {
                    imgDoor.visibility = if (isOpened) VISIBLE else GONE
                }
                .start()
        }
    }
class DoorDiffCallback : DiffUtil.ItemCallback<DoorsModel.Data>() {
    override fun areContentsTheSame(oldItem: DoorsModel.Data, newItem: DoorsModel.Data) = oldItem.id == newItem.id
    override fun areItemsTheSame(oldItem: DoorsModel.Data, newItem: DoorsModel.Data) = oldItem == newItem
}
