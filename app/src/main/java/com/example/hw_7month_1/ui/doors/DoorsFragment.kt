package com.example.hw_7month_1.ui.doors

import com.example.hw_7month_1.ui.doors.adapter.DoorsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_7month_1.databinding.FragmentDoorsBinding

class DoorsFragment : Fragment() {
    private lateinit var binding: FragmentDoorsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDoorsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAdapter()
    }

    private fun getAdapter() {
        val adapter = DoorsAdapter()
        binding.recyclerViewDoors.adapter = adapter
        binding.recyclerViewDoors.layoutManager = LinearLayoutManager(requireContext())
    }
}