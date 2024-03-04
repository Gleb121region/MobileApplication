package ru.spbstu.mobileapplication.presentation.authorization_activity.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.FragmentRestoreAccessBinding
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.authorization_activity.view_models.RestoreAccessViewModel
import javax.inject.Inject

class RestoreAccessFragment : Fragment() {
    private lateinit var viewModel: RestoreAccessViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentRestoreAccessBinding? = null
    private val binding: FragmentRestoreAccessBinding
        get() = _binding ?: throw RuntimeException("FragmentRestoreAccessBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestoreAccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[RestoreAccessViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        buttonSubmitListenerHandler()
        loginNowListenerHandler()
    }

    private fun buttonSubmitListenerHandler() {
        binding.buttonSubmit.setOnClickListener {
            Log.d(TAG, "buttonSubmit clicked")
            viewModel.restoreAccess(
                email = binding.editTextEmail.text.toString(),
                newPassword = binding.editTextNewPassword.text.toString(),
                confirmationPassword = binding.editTextConfirmationPassword.text.toString()
            ).observe(viewLifecycleOwner, Observer { result ->
                run {
                    Log.d(TAG, result.refreshToken)
                    Log.d(TAG, result.accessToken)
                    findNavController().navigate(R.id.action_restoreAccessFragment_to_basicActivity)
                }
            })
        }
    }

    private fun loginNowListenerHandler() {
        binding.loginNow.setOnClickListener {
            Log.d(TAG, "loginNow clicked")
            findNavController().navigate(R.id.action_restoreAccessFragment_to_signInFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        private const val TAG = "RestoreAccessFragment"
    }
}
