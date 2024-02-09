package com.example.hw_7month_1.presentation.doors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_7month_1.presentation.base.BaseFragment
import com.example.hw_7month_1.databinding.FragmentDoorsBinding
import com.example.hw_7month_1.presentation.doors.adapter.DoorsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoorsFragment : BaseFragment() {
    private lateinit var binding: FragmentDoorsBinding
    private val viewModel: DoorsViewModel by viewModels()
    private val adapter = DoorsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDoorsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh()
        viewModel.getDoors().stateHandler(
            success = {
                adapter.submitList(it)
                setupAdapter()
            }
        )
    }

    private fun setupAdapter() {
        binding.recyclerViewDoors.adapter = adapter
        binding.recyclerViewDoors.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun swipeRefresh() {
        binding.swipeRefreshDoors.setOnRefreshListener {
            viewModel.getDoors().stateHandler(
                success = {
                    adapter.submitList(it)
                    binding.swipeRefreshDoors.isRefreshing = false
                }
            )
        }
    }
}