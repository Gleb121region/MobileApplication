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
import dagger.hilt.android.AndroidEntryPoint
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.FragmentPurposeInterviewBinding
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.interview.view_models.PurposeViewModel
import javax.inject.Inject

@AndroidEntryPoint
class PurposeFragment : Fragment() {

    private lateinit var viewModel: PurposeViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentPurposeInterviewBinding? = null
    private val binding: FragmentPurposeInterviewBinding
        get() = _binding ?: throw RuntimeException("FragmentPurposeInterviewBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
        Log.d(TAG, "PurposeFragment onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPurposeInterviewBinding.inflate(inflater, container, false)
        Log.d(TAG, "PurposeFragment onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "PurposeFragment onViewCreating")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[PurposeViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        Log.d(TAG, "PurposeFragment onViewCreated")

        buttonJustLookListenerHandler()
        buttonRentOutListenerHandler()
        buttonRentListenerHandler()
    }

    private fun buttonJustLookListenerHandler() {
        binding.buttonJustLook.setOnClickListener {
            findNavController().navigate(R.id.action_purposeInterviewFragment_to_interviewRentTypeFragment)
        }
    }

    private fun buttonRentOutListenerHandler() {
        binding.buttonRentOut.setOnClickListener {
            findNavController().navigate(R.id.action_purposeInterviewFragment_to_interviewRentFragment)
        }
    }

    private fun buttonRentListenerHandler() {
        binding.buttonRent.setOnClickListener {
            findNavController().navigate(R.id.action_purposeInterviewFragment_to_interviewRentTypeFragment)
        }
    }

    private companion object {
        private const val TAG = "PurposeFragment"
    }
}
