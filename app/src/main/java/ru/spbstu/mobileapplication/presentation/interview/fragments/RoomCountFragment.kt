package ru.spbstu.mobileapplication.presentation.interview.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.presentation.interview.view_models.RoomViewModel

class RoomCountFragment : Fragment() {

    companion object {
        fun newInstance() = RoomCountFragment()
    }

    private lateinit var viewModel: RoomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_room_interview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RoomViewModel::class.java)
        // TODO: Use the ViewModel
    }

}