package com.example.hw_7month_1.ui.camers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_7month_1.databinding.FragmentCamerasBinding
import com.example.hw_7month_1.ui.camers.adapter.CameraAdapter

class CamerasFragment : Fragment() {
    private lateinit var binding: FragmentCamerasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCamerasBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        val adapter = CameraAdapter()
        binding.recyclerViewCameras.adapter = adapter
        binding.recyclerViewCameras.layoutManager = LinearLayoutManager(requireContext())
    }
}