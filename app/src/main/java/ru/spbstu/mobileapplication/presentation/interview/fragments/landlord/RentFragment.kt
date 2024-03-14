package ru.spbstu.mobileapplication.presentation.interview.fragments.landlord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.presentation.interview.view_models.landlord.RentViewModel

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