package ru.spbstu.mobileapplication.presentation.interview.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.presentation.interview.view_models.BudgetViewModel

class BudgetFragment : Fragment() {

    companion object {
        fun newInstance() = BudgetFragment()
    }

    private lateinit var viewModel: BudgetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_budget_interview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BudgetViewModel::class.java)
        // TODO: Use the ViewModel
    }

}