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
import ru.spbstu.mobileapplication.domain.enums.Role
import ru.spbstu.mobileapplication.databinding.FragmentSignUpBinding
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.authorization_activity.view_models.SignUpViewModel
import javax.inject.Inject

class SignUpFragment : Fragment() {
    private lateinit var viewModel: SignUpViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding
        get() = _binding ?: throw RuntimeException("FragmentSignUpBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[SignUpViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        buttonSubmitListenerHandler()
        textViewLoginListenerHandler()
    }

    private fun buttonSubmitListenerHandler() {
        binding.buttonSubmit.setOnClickListener {
            Log.d(TAG, "buttonSubmit clicked")
            viewModel.signUp(
                email = binding.editTextEmail.text.toString(),
                password = binding.editTextPassword.text.toString(),
                firstName = binding.editTextName.text.toString(),
                role = Role.USER.toString()
            ).observe(viewLifecycleOwner, Observer { result ->
                run {
                    Log.d(TAG, result.refreshToken)
                    Log.d(TAG, result.accessToken)
                    findNavController().navigate(R.id.action_signUpFragment_to_basicActivity)
                }
            })
        }
    }

    private fun textViewLoginListenerHandler() {
        binding.textViewLogin.setOnClickListener {
            Log.d(TAG, "textViewLogin clicked")
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        private const val TAG = "SignUpFragment"
    }
}
