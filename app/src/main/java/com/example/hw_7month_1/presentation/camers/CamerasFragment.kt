package com.example.hw_7month_1.presentation.camers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_7month_1.data.local.SmartHomeDao
import com.example.hw_7month_1.data.local.models.CameraData
import com.example.hw_7month_1.databinding.FragmentCamerasBinding
import com.example.hw_7month_1.presentation.base.BaseFragment
import com.example.hw_7month_1.presentation.camers.adapter.CamerasAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CamerasFragment : BaseFragment() {
    private lateinit var binding: FragmentCamerasBinding
    private val viewModel: CamerasViewModel by viewModels()
    private val adapter = CamerasAdapter()
    @Inject
    lateinit var dao: SmartHomeDao


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
        swipeRefresh()
    }

    private fun swipeRefresh() {
        if (dao.getCameraCount() == 0) {
            viewModel.getCameras().stateHandler(
                success = {
                    adapter.submitList(it.data.cameras)
                    val data = CameraData(
                        count = adapter.currentList.size
                    )
                    dao.insertCameraData(data)
                }
            )
        } else {
            binding.swipeRefreshCameras.setOnRefreshListener {
                viewModel.getCameras().stateHandler(
                    success = {
                        adapter.submitList(it.data.cameras)
                    }
                )
                binding.swipeRefreshCameras.isRefreshing = false
            }
        }
    }

    private fun setupAdapter() {
        binding.recyclerViewCameras.adapter = adapter
        binding.recyclerViewCameras.layoutManager = LinearLayoutManager(requireContext())
    }
}