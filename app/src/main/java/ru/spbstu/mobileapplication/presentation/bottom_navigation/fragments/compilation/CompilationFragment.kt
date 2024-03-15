package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.compilation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import dagger.hilt.android.AndroidEntryPoint
import ru.spbstu.mobileapplication.databinding.FragmentCompilationBinding
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.bottom_navigation.view_models.CompilationViewModel
import javax.inject.Inject

@AndroidEntryPoint
class CompilationFragment : Fragment(), CardStackListener {
    private lateinit var viewModel: CompilationViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentCompilationBinding? = null
    private val binding: FragmentCompilationBinding
        get() = _binding ?: throw RuntimeException("FragmentCompilationBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
        Log.d(TAG, "CompilationFragment onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompilationBinding.inflate(inflater, container, false)
        Log.d(TAG, "CompilationFragment onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "CompilationFragment onViewCreating")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[CompilationViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        Log.d(TAG, "CompilationFragment onViewCreated")
    }

    override fun onCardDragging(direction: Direction, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
    }

    override fun onCardRewound() {
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }


    private companion object {
        private const val TAG = "CompilationFragment"
    }
}