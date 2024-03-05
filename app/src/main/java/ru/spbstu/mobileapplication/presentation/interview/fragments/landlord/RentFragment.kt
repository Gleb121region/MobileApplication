package ru.spbstu.mobileapplication.presentation.interview.fragments.landlord

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.spbstu.mobileapplication.presentation.interview.view_models.landlord.RentViewModel
import ru.spbstu.mobileapplication.R

class RentFragment : Fragment() {

    companion object {
        fun newInstance() = RentFragment()
    }

    private lateinit var viewModel: RentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_interview_rent, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}