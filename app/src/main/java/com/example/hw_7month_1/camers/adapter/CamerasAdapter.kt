package com.example.hw_7month_1.camers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.hw_7month_1.databinding.ItemCamerasBinding
import com.example.hw_7month_1.response.CamerasModel

class CamerasAdapter : ListAdapter<CamerasModel.Data.Camera, CameraViewHolder>(
    CameraDiffCallback()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CameraViewHolder {
        return CameraViewHolder(
            ItemCamerasBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CameraViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CameraViewHolder(private val binding: ItemCamerasBinding) : ViewHolder(binding.root) {
    fun bind(position: CamerasModel.Data.Camera) {
        binding.tvCamera.text = position.name
        Glide.with(binding.ivCamera).load(position.snapshot).into(binding.ivCamera)
    }
}

class CameraDiffCallback : DiffUtil.ItemCallback<CamerasModel.Data.Camera>() {
    override fun areContentsTheSame(oldItem: CamerasModel.Data.Camera, newItem: CamerasModel.Data.Camera) = oldItem.id == newItem.id

    override fun areItemsTheSame(oldItem: CamerasModel.Data.Camera, newItem: CamerasModel.Data.Camera) = oldItem == newItem
}