package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.spbstu.mobileapplication.databinding.FragmentHomeBinding
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.GetTokenFromLocalStorageUseCase
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.bottom_navigation.view_models.HomeViewModel
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
        Log.d(TAG, "HomeFragment onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        Log.d(TAG, "HomeFragment onCreateView")
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "HomeFragment onViewCreating")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        lifecycleScope.launch {
            val token = "Bearer ${getTokenFromLocalStorageUseCase().accessToken}"
            Log.d(TAG, token)
            val model = viewModel.getLastSurveyFromDB()
            Log.d(TAG, model.toString())
            try {
                val announcements =
                    viewModel.sendRequest(lastSurvey = model, limit = 10, offset = 0, token = token)

                val announcementAdapter = AnnouncementAdapter(announcements)
                binding.rvShopList.adapter = announcementAdapter
            } catch (e: Exception) {
                // Обработка исключений
                Log.e(TAG, "Ошибка при получении объявлений", e)
                // Здесь вы можете добавить дополнительную логику для обработки ошибок, например, показать сообщение пользователю
            }
        }

        Log.d(TAG, "HomeFragment onViewCreated")
    }



    private companion object {
        private const val TAG = "HomeFragment"
    }
}
