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
import androidx.navigation.fragment.navArgs
import ru.spbstu.mobileapplication.databinding.FragmentBudgetInterviewBinding
import ru.spbstu.mobileapplication.domain.survey.entity.SurveyResult
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.interview.view_models.BudgetViewModel
import javax.inject.Inject

class BudgetFragment : Fragment() {

    private val args by navArgs<BudgetFragmentArgs>()

    private lateinit var viewModel: BudgetViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentBudgetInterviewBinding? = null
    private val binding: FragmentBudgetInterviewBinding
        get() = _binding ?: throw RuntimeException("FragmentInterviewAreaBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
        Log.d(TAG, "BudgetFragment onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBudgetInterviewBinding.inflate(inflater, container, false)
        Log.d(TAG, "BudgetFragment onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "BudgetFragment onViewCreating")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[BudgetViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        with(binding) {
            // todo: надо обработать случаи когда не то пытаются закинуть в int!
//            val minBudget = editTextMinSpace.text.toString().toIntOrNull()
//            val maxBudget = editTextMaxSpace.text.toString().toIntOrNull()
            buttonSearch.setOnClickListener {
                launchFlatCity(
                    editTextMinSpace.text.toString().toInt(),
                    editTextMaxSpace.text.toString().toInt()
                )
            }
        }
        //        buttonBackListenerHandler()

        Log.d(TAG, "Survey Result: $args.surveyResult")
        Log.d(TAG, "BudgetFragment onViewCreated")
    }

//    private fun buttonBackListenerHandler() {
//         обработать кнопку назад
//        TODO("Not yet implemented")
//    }


    private fun launchFlatCity(minBudget: Int, maxBudget: Int) {
        findNavController().navigate(
            BudgetFragmentDirections.actionBudgetFragmentToCityFragment(
                SurveyResult(
                    args.surveyResult.term,
                    args.surveyResult.apartmentTypes,
                    null,
                    args.surveyResult.minArea,
                    args.surveyResult.maxArea,
                    minBudget,
                    maxBudget
                )
            )
        )
    }

    private companion object {
        private const val TAG = "BudgetFragment"
    }

}