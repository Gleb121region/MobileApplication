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
import ru.spbstu.mobileapplication.databinding.FragmentFavoriteBinding
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.bottom_navigation.view_models.FavoriteViewModel
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding ?: throw RuntimeException("FragmentFavoriteBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
        Log.d(TAG, "FavoriteFragment onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        Log.d(TAG, "FavoriteFragment onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "FavoriteFragment onViewCreating")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavoriteViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        Log.d(TAG, "FavoriteFragment onViewCreated")
    }


    private companion object {
        private const val TAG = "FavoriteFragment"
    }
}