package com.example.hw_7month_1.ui.camers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hw_7month_1.databinding.ItemCamerasBinding

class CameraAdapter : RecyclerView.Adapter<CameraAdapter.CameraViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraViewHolder {
        return CameraViewHolder(
            ItemCamerasBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 25
    }

    override fun onBindViewHolder(holder: CameraViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class CameraViewHolder(private val binding: ItemCamerasBinding) :
        ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.tvCamera.text = "Камера 1"
        }
    }
}