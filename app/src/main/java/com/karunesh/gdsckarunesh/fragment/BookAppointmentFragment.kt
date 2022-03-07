package com.karunesh.gdsckarunesh.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.karunesh.gdsckarunesh.R
import com.karunesh.gdsckarunesh.adapter.slot.SlotsAdapter
import com.karunesh.gdsckarunesh.business.slot.SlotsViewModel
import com.karunesh.gdsckarunesh.databinding.FragmentBookAppointmentBinding
import com.karunesh.gdsckarunesh.model.Slot
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

@AndroidEntryPoint
class BookAppointmentFragment : Fragment() {

    private lateinit var binding : FragmentBookAppointmentBinding
    private var startTime: MaterialTimePicker? = null
    private var endTime: MaterialTimePicker? = null
    private val sdf = SimpleDateFormat("dd/M/yyyy", Locale.US)
    private val currentDate = sdf.format(Date())
    private val viewModel: SlotsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment\
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_book_appointment,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.slotStatus.observe(viewLifecycleOwner,{slotStatus ->
            if (slotStatus){
                Toast.makeText(context,"Slots have been created",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"Failed to create Slots. Please Try again later",Toast.LENGTH_LONG).show()
            }
        })

        binding.viewCreatedSlotsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_bookAppointmentFragment_to_viewCreatedSlotsFragment)
        }

        binding.startTimeBtn.setOnClickListener {

            startTime =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setMinute(30)
                    .setTitleText("Start Time")
                    .build()

            startTime!!.show(childFragmentManager, "")
            startTime?.addOnPositiveButtonClickListener {
                val startTimeTxt = "${startTime?.hour} : ${startTime?.minute}"
                binding.startTimeTxt.text = startTimeTxt
            }
        }

        binding.endTimeBtn.setOnClickListener {
            endTime =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setMinute(30)
                    .setTitleText("End Time")
                    .build()

            endTime!!.show(childFragmentManager, "")

            endTime?.addOnPositiveButtonClickListener {
                val endTimeTxt = "${endTime?.hour} : ${endTime?.minute}"
                binding.endTimeTxt.text = endTimeTxt
            }
        }




        binding.addBtn.setOnClickListener {


            binding.intervalEdTxt.text?.let {
                val slots = TimeSlot(
                    LocalTime.of(startTime?.hour!!, startTime?.minute!!),
                    LocalTime.of(endTime?.hour!!, endTime?.minute!!)
                ).divide(it.toString().toLong())
                slots.forEach { timeSlot ->
                    val slot = Slot()
                    slot.slotId = UUID.randomUUID().toString()
                    slot.slotDate = currentDate
                    slot.slotStartTime = timeSlot.startTime.toString()
                    slot.slotEndTime = timeSlot.endTime.toString()
                    Log.d("Slot","The slot is $slot")
                    lifecycleScope.launch {
                        viewModel.addSlots(slot)
                    }
                }
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun TimeSlot.divide(lengthMinutes: Long): List<TimeSlot> {
        val timeSlots = mutableListOf<TimeSlot>()
        var nextStartTime = startTime
        while (true) {
            val nextEndTime = nextStartTime.plusMinutes(lengthMinutes)
            if (nextEndTime > endTime) {
                break
            }
            timeSlots.add(TimeSlot(nextStartTime, nextEndTime))
            nextStartTime = nextEndTime
        }
        return timeSlots
    }

    data class TimeSlot(val startTime: LocalTime, val endTime: LocalTime)

}