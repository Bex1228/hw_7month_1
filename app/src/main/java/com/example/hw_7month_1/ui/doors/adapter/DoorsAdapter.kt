package com.example.hw_7month_1.ui.doors.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_7month_1.databinding.ItemDoorsBinding

class DoorsAdapter : RecyclerView.Adapter<DoorsAdapter.DoorsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoorsViewHolder {
        return DoorsViewHolder(
            ItemDoorsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: DoorsViewHolder, position: Int) {
        holder.bind()
    }

    inner class DoorsViewHolder(private val binding: ItemDoorsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isOpened = false

        init {
            itemView.setOnClickListener {
                toggleDoor()
            }
        }

        fun bind() {
            binding.imgDoor.visibility = if (isOpened) VISIBLE else GONE
        }

        private fun toggleDoor()= with(binding) {
            isOpened = !isOpened
            val translationY = if (isOpened) 0f else imgDoor.height.toFloat()

            imgDoor.animate()
                .translationY(translationY)
                .setDuration(500)
                .withEndAction {
                    imgDoor.visibility = if (isOpened) View.VISIBLE else View.GONE
                }
                .start()
        }
    }
}
