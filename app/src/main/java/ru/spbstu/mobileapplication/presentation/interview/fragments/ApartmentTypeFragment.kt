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
import ru.spbstu.mobileapplication.databinding.FragmentRoomInterviewBinding
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.interview.view_models.ApartmentTypeViewModel
import javax.inject.Inject

class ApartmentTypeFragment : Fragment() {

    private lateinit var viewModel: ApartmentTypeViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentRoomInterviewBinding? = null
    private val binding: FragmentRoomInterviewBinding
        get() = _binding ?: throw RuntimeException("FragmentRoomInterviewBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
        Log.d(TAG, "ApartmentTypeFragment onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomInterviewBinding.inflate(inflater, container, false)
        Log.d(TAG, "ApartmentTypeFragment onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "ApartmentTypeFragment onViewCreating")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[ApartmentTypeViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        Log.d(TAG, "ApartmentTypeFragment onViewCreated")

//        buttonBackListenerHandler()

        buttonFewMonthsListenerHandler()
    }

//    private fun buttonBackListenerHandler() {
//         обработать кнопку назад
//        TODO("Not yet implemented")
//    }


    private fun buttonFewMonthsListenerHandler() {
        binding.buttonStudio.setOnClickListener {
            findNavController().navigate(R.id.action_roomInterviewFragment_to_areaFragment)
        }
        binding.buttonOne.setOnClickListener {
            findNavController().navigate(R.id.action_roomInterviewFragment_to_areaFragment)
        }
        binding.buttonTwo.setOnClickListener {
            findNavController().navigate(R.id.action_roomInterviewFragment_to_areaFragment)
        }
        binding.buttonThree.setOnClickListener {
            findNavController().navigate(R.id.action_roomInterviewFragment_to_areaFragment)
        }
        binding.buttonFour.setOnClickListener {
            findNavController().navigate(R.id.action_roomInterviewFragment_to_areaFragment)
        }
        binding.buttonFive.setOnClickListener {
            findNavController().navigate(R.id.action_roomInterviewFragment_to_areaFragment)
        }
    }

    private companion object {
        private const val TAG = "ApartmentTypeFragment"
    }

}