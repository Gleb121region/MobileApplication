package ru.spbstu.mobileapplication.presentation.interview.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.spbstu.mobileapplication.presentation.interview.view_models.PurposeViewModel
import ru.spbstu.mobileapplication.R

class PurposeFragment : Fragment() {

    companion object {
        fun newInstance() = PurposeFragment()
    }

    private lateinit var viewModel: PurposeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_purpose_interview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PurposeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}