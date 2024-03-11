package ru.spbstu.mobileapplication.presentation.interview.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.FragmentInterviewAreaBinding
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.interview.view_models.AreaViewModel
import javax.inject.Inject

class AreaFragment : Fragment() {

    private lateinit var viewModel: AreaViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentInterviewAreaBinding? = null
    private val binding: FragmentInterviewAreaBinding
        get() = _binding ?: throw RuntimeException("FragmentInterviewAreaBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
        Log.d(TAG, "AreaFragment onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInterviewAreaBinding.inflate(inflater, container, false)
        Log.d(TAG, "AreaFragment onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "AreaFragment onViewCreating")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[AreaViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        Log.d(TAG, "AreaFragment onViewCreated")

//        buttonBackListenerHandler()

        buttonFewMonthsListenerHandler()
    }

//    private fun buttonBackListenerHandler() {
//         обработать кнопку назад
//        TODO("Not yet implemented")
//    }


    private fun buttonFewMonthsListenerHandler() {
        binding.buttonSearch.setOnClickListener {
            findNavController().navigate(R.id.action_areaFragment_to_budgetFragment)
        }
    }

    private companion object {
        private const val TAG = "AreaFragment"
    }

}