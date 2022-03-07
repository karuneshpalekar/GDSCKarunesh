package com.karunesh.gdsckarunesh.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.karunesh.gdsckarunesh.R
import com.karunesh.gdsckarunesh.adapter.slot.SlotsAdapter
import com.karunesh.gdsckarunesh.business.slot.SlotsViewModel
import com.karunesh.gdsckarunesh.databinding.FragmentViewCreatedSlotsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ViewCreatedSlotsFragment : Fragment() {

    private lateinit var binding : FragmentViewCreatedSlotsBinding
    private val viewModel: SlotsViewModel by viewModels()
    private val adapter = SlotsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_view_created_slots,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.getData()
        }

        viewModel.data.observe(viewLifecycleOwner,{ data ->
            adapter.setData(data)
        })

    }


}