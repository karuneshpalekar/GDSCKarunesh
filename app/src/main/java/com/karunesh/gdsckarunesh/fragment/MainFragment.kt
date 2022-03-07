package com.karunesh.gdsckarunesh.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.karunesh.gdsckarunesh.R
import com.karunesh.gdsckarunesh.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bookAppointment.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_bookAppointmentFragment)
        }

        binding.customerDetails.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_customerDetailsFragment)
        }

        binding.shopDetails.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_shopDetailsFragment)
        }

    }



}