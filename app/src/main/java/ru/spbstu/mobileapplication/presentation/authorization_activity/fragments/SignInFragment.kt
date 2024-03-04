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
import dagger.hilt.android.AndroidEntryPoint
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.FragmentSignInBinding
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.authorization_activity.view_models.SignInViewModel
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var viewModel: SignInViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding
        get() = _binding ?: throw RuntimeException("FragmentSignInBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[SignInViewModel::class.java]

        binding.signInViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        buttonSubmitListenerHandler()
        textViewRegisterListenerHandler()
        textViewResetPasswordListenerHandler()
    }

    private fun textViewResetPasswordListenerHandler() {
        binding.textViewResetPassword.setOnClickListener {
            Log.d(TAG, "textViewResetPassword clicked")
            findNavController().navigate(R.id.action_signInFragment_to_restoreAccessFragment)
        }
    }

    private fun textViewRegisterListenerHandler() {
        binding.textViewRegister.setOnClickListener {
            Log.d(TAG, "textViewRegister clicked")
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    private fun buttonSubmitListenerHandler() {
        binding.buttonSubmit.setOnClickListener {
            Log.d(TAG, "buttonSubmit clicked")
            viewModel.signIn(
                email = binding.editTextEmail.text.toString(),
                password = binding.editTextPassword.text.toString()
            )
            viewModel.signInResult.observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is SignInViewModel.SignInResult.Success -> {
                        Log.d(TAG, result.tokenItem.accessToken)
                        Log.d(TAG, result.tokenItem.refreshToken)
                        findNavController().navigate(R.id.action_signInFragment_to_basicActivity)
                    }

                    is SignInViewModel.SignInResult.Error -> {
                        binding.textViewError.text = result.message
                        binding.textViewError.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        private const val TAG = "SignInFragment"
    }
}
