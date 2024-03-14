package ru.spbstu.mobileapplication.presentation.interview.fragments.city

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.spbstu.mobileapplication.databinding.FragmentCityInterviewBinding
import ru.spbstu.mobileapplication.domain.enums.interview.City
import ru.spbstu.mobileapplication.domain.survey_answers.entity.SurveyResult
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.interview.view_models.CityViewModel
import javax.inject.Inject

class CityFragment : Fragment() {
    private val args by navArgs<CityFragmentArgs>()

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
        val cityAdapter =
            CityAdapter(City.entries, object : CityAdapter.OnCityClickListener {
                override fun onCityClick(city: City) {
                    launchMainActivity(city)
                }
            })
        binding.recyclerViewCities.adapter = cityAdapter
        //        buttonBackListenerHandler()

        Log.d(TAG, "Survey Result: $args.surveyResult")
        Log.d(TAG, "CityFragment onViewCreated")
    }

    private fun launchMainActivity(selectedCity: City) {
        findNavController().navigate(
            CityFragmentDirections.actionCityFragmentToBottomNavigationActivity2(
                SurveyResult(
                    args.surveyResult.term,
                    args.surveyResult.apartmentType,
                    selectedCity,
                    args.surveyResult.minArea,
                    args.surveyResult.maxArea,
                    args.surveyResult.minBudget,
                    args.surveyResult.maxBudget
                )
            )
        )
    }

    private companion object {
        private const val TAG = "CityFragment"
    }

}