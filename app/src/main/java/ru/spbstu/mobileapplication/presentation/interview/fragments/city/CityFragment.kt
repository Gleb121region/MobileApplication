package ru.spbstu.mobileapplication.presentation.interview.fragments.city

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
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
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[CityViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val cityAdapter =
            CityAdapter(City.entries, object : CityAdapter.OnCityClickListener {
                override fun onCityClick(city: City) {
                    lifecycleScope.launch {
                        insetIntoDB(city)
                        launchMainActivity()
                    }
                }
            })
        binding.recyclerViewCities.adapter = cityAdapter
    }

    private suspend fun insetIntoDB(selectedCity: City) {
        val surveyResult = SurveyResult(
            args.surveyResult.term,
            args.surveyResult.apartmentTypes,
            selectedCity,
            args.surveyResult.minArea,
            args.surveyResult.maxArea,
            args.surveyResult.minBudget,
            args.surveyResult.maxBudget
        )
        viewModel.recordIntoDB(surveyResult)
        viewModel.sendRequest(surveyResult)
    }

    private fun launchMainActivity() {
        findNavController().navigate(CityFragmentDirections.actionCityFragmentToBottomNavigationActivity2())
    }

    private companion object {
        private const val TAG = "CityFragment"
    }

}