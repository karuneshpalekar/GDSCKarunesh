package com.karunesh.gdsckarunesh.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.karunesh.gdsckarunesh.R
import com.karunesh.gdsckarunesh.adapter.user.UserAdapter
import com.karunesh.gdsckarunesh.adapter.user.UserListener
import com.karunesh.gdsckarunesh.business.user.UserDetailViewModel
import com.karunesh.gdsckarunesh.databinding.FragmentCustomerDetailsBinding
import com.karunesh.gdsckarunesh.model.Users
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class CustomerDetailsFragment : Fragment(), UserListener {

    private lateinit var binding: FragmentCustomerDetailsBinding
    private val viewModel: UserDetailViewModel by viewModels()
    private val adapter: UserAdapter = UserAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_customer_details, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewCustomerDetails.adapter = adapter
        adapter.listener = this
        lifecycleScope.launch {
            viewModel.getData()
        }
        viewModel.data.observe(viewLifecycleOwner, { list ->

            adapter.setData(list)
        })

        viewModel.blockValue.observe(viewLifecycleOwner,{ value ->
            if (value){
                Toast.makeText(context,"User has been blocked",Toast.LENGTH_LONG).show()
            }else {
                Toast.makeText(context,"User has not been blocked. Please try again later",Toast.LENGTH_LONG).show()
            }
        })

    }

    override fun blockUser(user: Users) {
        lifecycleScope.launch {
            viewModel.blockUser("shopId",user)
        }
    }

}