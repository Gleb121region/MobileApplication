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
import ru.spbstu.mobileapplication.databinding.FragmentCityInterviewBinding
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.interview.view_models.CityViewModel
import javax.inject.Inject

class CityFragment : Fragment() {

    private lateinit var viewModel: CityViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentCityInterviewBinding? = null
    private val binding: FragmentCityInterviewBinding
        get() = _binding ?: throw RuntimeException("FragmentCityInterviewBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
        Log.d(TAG, "CityFragment onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityInterviewBinding.inflate(inflater, container, false)
        Log.d(TAG, "CityFragment onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "CityFragment onViewCreating")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[CityViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        Log.d(TAG, "CityFragment onViewCreated")

//        buttonBackListenerHandler()

        buttonFewMonthsListenerHandler()
    }

//    private fun buttonBackListenerHandler() {
//         обработать кнопку назад
//        TODO("Not yet implemented")
//    }


    private fun buttonFewMonthsListenerHandler() {
        binding.recyclerViewCities.setOnClickListener {
            findNavController().navigate(R.id.action_cityFragment_to_bottomNavigationActivity2)
        }
    }

    private companion object {
        private const val TAG = "CityFragment"
    }

}