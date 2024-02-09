package com.example.hw_7month_1.presentation.camers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_7month_1.presentation.base.BaseFragment
import com.example.hw_7month_1.presentation.camers.adapter.CamerasAdapter
import com.example.hw_7month_1.databinding.FragmentCamerasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CamerasFragment : BaseFragment() {
    private lateinit var binding: FragmentCamerasBinding
    private val viewModel: CamerasViewModel by viewModels()
    private val adapter = CamerasAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCamerasBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh()
        viewModel.getCameras().stateHandler(
            success = {
                adapter.submitList(it)
                setupAdapter()
            }
        )
    }

    private fun setupAdapter() {
        binding.recyclerViewCameras.adapter = adapter
        binding.recyclerViewCameras.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun swipeRefresh() {
        binding.swipeRefreshCameras.setOnRefreshListener {
            viewModel.getCameras().stateHandler(
                success = {
                    adapter.submitList(it)
                    binding.swipeRefreshCameras.isRefreshing = false
                }
            )
        }
    }
}