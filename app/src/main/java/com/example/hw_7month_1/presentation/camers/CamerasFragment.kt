package com.example.hw_7month_1.presentation.camers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_7month_1.data.local.SmartHomeDao
import com.example.hw_7month_1.data.local.models.DoorData
import com.example.hw_7month_1.databinding.FragmentCamerasBinding
import com.example.hw_7month_1.domain.utils.UiState
import com.example.hw_7month_1.presentation.camers.adapter.CamerasAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CamerasFragment : Fragment() {
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
        initRequest()
        setupAdapter()
        swipeRefresh()
    }

    private fun swipeRefresh() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.camerasFlow.collect{
                when(it){
                    is UiState.Empty -> {
                        adapter.submitList(emptyList())
                        adapter.notifyDataSetChanged()
                    }
                    is UiState.Error -> {
                        Toast.makeText(requireContext(), "Произошла ошибка", Toast.LENGTH_LONG).show()
                    }
                    is UiState.Loading -> {}
                    is UiState.Success -> {
                        if (dao.getDoorCount() == 0) {
                            viewModel.viewModelScope.launch {
                                viewModel.getCameras()
                                adapter.submitList(it.data?.data?.cameras)
                                val data = DoorData(
                                    count = adapter.currentList.size
                                )
                                dao.insertDoorData(data)
                            }
                        }else{
                            binding.swipeRefreshCameras.setOnRefreshListener {
                                viewModel.viewModelScope.launch {
                                    viewModel.getCameras()
                                    adapter.submitList(it.data?.data?.cameras)
                                }
                                binding.swipeRefreshCameras.isRefreshing = false
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        binding.recyclerViewCameras.adapter = adapter
        binding.recyclerViewCameras.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initRequest() {
        viewModel.viewModelScope.launch { viewModel.getCameras() }
    }
}