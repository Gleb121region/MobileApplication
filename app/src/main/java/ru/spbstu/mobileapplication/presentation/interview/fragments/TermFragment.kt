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
import ru.spbstu.mobileapplication.databinding.FragmentInterviewTermBinding
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.interview.view_models.TermViewModel
import javax.inject.Inject

class TermFragment : Fragment() {

    private lateinit var viewModel: TermViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentInterviewTermBinding? = null
    private val binding: FragmentInterviewTermBinding
        get() = _binding ?: throw RuntimeException("FragmentInterviewTermBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
        Log.d(TAG, "TermFragment onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInterviewTermBinding.inflate(inflater, container, false)
        Log.d(TAG, "TermFragment onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "TermFragment onViewCreating")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[TermViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        Log.d(TAG, "TermFragment onViewCreated")

//        buttonBackListenerHandler()

        buttonFewDaysOutListenerHandler()
        buttonFewMonthsListenerHandler()
    }

//    private fun buttonBackListenerHandler() {
//         обработать кнопку назад
//        TODO("Not yet implemented")
//    }

    private fun buttonFewDaysOutListenerHandler() {
        binding.buttonFewDays.setOnClickListener {
            findNavController().navigate(R.id.action_interviewRentTypeFragment_to_roomInterviewFragment)
        }
    }

    private fun buttonFewMonthsListenerHandler() {
        binding.buttonFewMonths.setOnClickListener {
            findNavController().navigate(R.id.action_interviewRentTypeFragment_to_roomInterviewFragment)
        }
    }

    private companion object {
        private const val TAG = "TermFragment"
    }
}