package com.example.hw_7month_1.presentation.doors

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
import com.example.hw_7month_1.databinding.FragmentDoorsBinding
import com.example.hw_7month_1.domain.utils.UiState
import com.example.hw_7month_1.presentation.doors.adapter.DoorsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DoorsFragment : Fragment() {
    private lateinit var binding: FragmentDoorsBinding
    private val viewModel: DoorsViewModel by viewModels()
    private val adapter = DoorsAdapter()

    @Inject
    lateinit var dao: SmartHomeDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDoorsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRequest()
        setupAdapter()
        swipeRefresh()
    }

    private fun setupAdapter() {
        binding.recyclerViewDoors.adapter = adapter
        binding.recyclerViewDoors.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun swipeRefresh() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.doorsFlow.collect {
                when (it) {
                    is UiState.Empty -> {
                        adapter.submitList(emptyList())
                        adapter.notifyDataSetChanged()
                    }
                    is UiState.Error -> {
                        Toast.makeText(requireContext(), "Произошла ошибка", Toast.LENGTH_LONG)
                            .show()
                    }
                    is UiState.Loading -> {}
                    is UiState.Success -> {
                        if (dao.getDoorCount() == 0) {
                            viewModel.viewModelScope.launch {
                                viewModel.getDoors()
                                adapter.submitList(it.data?.data)
                                val data = DoorData(
                                    count = adapter.currentList.size
                                )
                                dao.insertDoorData(data)
                            }
                        }else{
                            binding.swipeRefreshDoors.setOnRefreshListener {
                                viewModel.viewModelScope.launch {
                                    viewModel.getDoors()
                                    adapter.submitList(it.data?.data)
                                }
                                binding.swipeRefreshDoors.isRefreshing = false
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initRequest() {
        viewModel.viewModelScope.launch { viewModel.getDoors() }
    }
}