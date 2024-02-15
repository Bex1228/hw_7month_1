package com.example.hw_7month_1.presentation.doors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_7month_1.data.local.SmartHomeDao
import com.example.hw_7month_1.data.local.models.DoorData
import com.example.hw_7month_1.databinding.FragmentDoorsBinding
import com.example.hw_7month_1.presentation.base.BaseFragment
import com.example.hw_7month_1.presentation.doors.adapter.DoorsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DoorsFragment : BaseFragment() {
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
        setupAdapter()
        swipeRefresh()
    }

    private fun setupAdapter() {
        binding.recyclerViewDoors.adapter = adapter
        binding.recyclerViewDoors.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun swipeRefresh() {
        if (dao.getDoorCount() == 0) {
            viewModel.getDoors().stateHandler(
                success = {
                    adapter.submitList(it.data)
                    val data = DoorData(
                        count = adapter.currentList.size
                    )
                    dao.insertDoorData(data)
                }
            )
        } else {
            binding.swipeRefreshDoors.setOnRefreshListener {
                viewModel.getDoors().stateHandler(
                    success = {
                        adapter.submitList(it.data)
                    }
                )
                binding.swipeRefreshDoors.isRefreshing = false
            }
        }
    }
}