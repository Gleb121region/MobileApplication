package ru.spbstu.mobileapplication.presentation.interview.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.button.MaterialButton
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.FragmentRoomInterviewBinding
import ru.spbstu.mobileapplication.domain.enums.interview.ApartmentType
import ru.spbstu.mobileapplication.domain.survey_answers.entity.SurveyResult
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.interview.view_models.ApartmentTypeViewModel
import javax.inject.Inject

class ApartmentTypeFragment : Fragment() {

    private val args by navArgs<ApartmentTypeFragmentArgs>()

    private var selectedApartmentTypes: MutableSet<ApartmentType> = mutableSetOf()

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

        with(binding) {
            buttonStudio.setOnClickListener {
                toggleApartmentTypeSelection(ApartmentType.STUDIO)
            }
            buttonOne.setOnClickListener {
                toggleApartmentTypeSelection(ApartmentType.ONE_ROOM_APARTMENT)
            }
            buttonTwo.setOnClickListener {
                toggleApartmentTypeSelection(ApartmentType.TWO_ROOM_APARTMENT)
            }
            buttonThree.setOnClickListener {
                toggleApartmentTypeSelection(ApartmentType.THREE_ROOM_APARTMENT)
            }
            buttonFour.setOnClickListener {
                toggleApartmentTypeSelection(ApartmentType.FOUR_ROOM_APARTMENT)
            }
            buttonFive.setOnClickListener {
                toggleApartmentTypeSelection(ApartmentType.FIVE_ROOM_APARTMENT)
            }
            buttonSearch.setOnClickListener {
                launchFlatArea(selectedApartmentTypes)
            }
        }
        Log.d(TAG, "Survey Result: $args.surveyResult")
        Log.d(TAG, "ApartmentTypeFragment onViewCreated")

//        buttonBackListenerHandler()

    }

//    private fun buttonBackListenerHandler() {
//         обработать кнопку назад
//        TODO("Not yet implemented")
//    }

    private fun toggleApartmentTypeSelection(apartmentType: ApartmentType) {
        val buttonId = when (apartmentType) {
            ApartmentType.STUDIO -> R.id.buttonStudio
            ApartmentType.ONE_ROOM_APARTMENT -> R.id.buttonOne
            ApartmentType.TWO_ROOM_APARTMENT -> R.id.buttonTwo
            ApartmentType.THREE_ROOM_APARTMENT -> R.id.buttonThree
            ApartmentType.FOUR_ROOM_APARTMENT -> R.id.buttonFour
            ApartmentType.FIVE_ROOM_APARTMENT -> R.id.buttonFive
        }

        val button = binding.root.findViewById<MaterialButton>(buttonId)
        if (selectedApartmentTypes.contains(apartmentType)) {
            selectedApartmentTypes.remove(apartmentType)
            button.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.unselected_button_color
                )
            )
        } else {
            selectedApartmentTypes.add(apartmentType)
            button.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.selected_button_color
                )
            )
        }
    }


    private fun launchFlatArea(apartmentTypes: Set<ApartmentType>) {
        findNavController().navigate(
            ApartmentTypeFragmentDirections.actionRoomInterviewFragmentToAreaFragment(
                surveyResult = SurveyResult(
                    args.surveyResult.term,
                    apartmentTypes,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
        )
    }

    private companion object {
        private const val TAG = "ApartmentTypeFragment"
    }

}