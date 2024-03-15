package ru.spbstu.mobileapplication.presentation.interview.fragments

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
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.FragmentPurposeInterviewBinding
import ru.spbstu.mobileapplication.domain.enums.interview.ApartmentType
import ru.spbstu.mobileapplication.domain.enums.interview.City
import ru.spbstu.mobileapplication.domain.enums.interview.Term
import ru.spbstu.mobileapplication.domain.survey_answers.entity.SurveyResult
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

        with(binding) {
            buttonJustLookListenerHandler(buttonJustLook)
            buttonRentOutListenerHandler(buttonRentOut)
            buttonRentListenerHandler(buttonRent)
        }

        Log.d(TAG, "PurposeFragment onViewCreated")
    }

    private fun buttonJustLookListenerHandler(button: MaterialButton) {
        button.setOnClickListener {
            lifecycleScope.launch {
                insetIntoDB()
                findNavController().navigate(R.id.action_purposeInterviewFragment_to_bottomNavigationActivity2)
            }
        }
    }

    private fun buttonRentOutListenerHandler(button: MaterialButton) {
        button.setOnClickListener {
            findNavController().navigate(R.id.action_purposeInterviewFragment_to_interviewRentFragment)
        }
    }

    private fun buttonRentListenerHandler(button: MaterialButton) {
        button.setOnClickListener {
            findNavController().navigate(R.id.action_purposeInterviewFragment_to_interviewRentTypeFragment)
        }
    }


    private suspend fun insetIntoDB() {
        val surveyResult = SurveyResult(
            Term.LONG,
            setOf<ApartmentType>(
                ApartmentType.STUDIO,
                ApartmentType.ONE_ROOM_APARTMENT,
                ApartmentType.TWO_ROOM_APARTMENT,
                ApartmentType.THREE_ROOM_APARTMENT
            ),
            City.MOSCOW,
            0,
            150,
            0,
            1_000_000
        )
        viewModel.recordIntoDB(surveyResult)
        viewModel.sendRequest(surveyResult)
    }

    private companion object {
        private const val TAG = "PurposeFragment"
    }
}
