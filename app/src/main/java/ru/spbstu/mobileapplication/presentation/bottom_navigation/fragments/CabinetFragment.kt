package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import ru.spbstu.mobileapplication.databinding.FragmentCabinetBinding
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.bottom_navigation.view_models.CabinetViewModel
import javax.inject.Inject

@AndroidEntryPoint
class CabinetFragment : Fragment() {

    private lateinit var viewModel: CabinetViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentCabinetBinding? = null
    private val binding: FragmentCabinetBinding
        get() = _binding ?: throw RuntimeException("FragmentCabinetBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
        Log.d(TAG, "CabinetFragment onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCabinetBinding.inflate(inflater, container, false)
        Log.d(TAG, "CabinetFragment onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "CabinetFragment onViewCreating")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[CabinetViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        Log.d(TAG, "CabinetFragment onViewCreated")
    }


    private companion object {
        private const val TAG = "CabinetFragment"
    }
}