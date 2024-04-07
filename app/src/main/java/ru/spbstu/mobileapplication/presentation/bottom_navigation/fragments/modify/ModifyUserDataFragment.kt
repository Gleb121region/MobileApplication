package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.modify

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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.FragmentModifyUserDataBinding
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.GetTokenFromLocalStorageUseCase
import ru.spbstu.mobileapplication.domain.enums.Gender
import ru.spbstu.mobileapplication.domain.user.entity.EditUserItem
import ru.spbstu.mobileapplication.domain.user.entity.UserItem
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class ModifyUserDataFragment : Fragment() {

    private lateinit var viewModel: ModifyUserDataViewModel

    @Inject
    lateinit var getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentModifyUserDataBinding? = null
    private val binding: FragmentModifyUserDataBinding
        get() = _binding ?: throw RuntimeException("FragmentCabinetBinding is null")

    private var currentUserId: Int = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
        Log.d(TAG, "CabinetFragment onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModifyUserDataBinding.inflate(inflater, container, false)
        Log.d(TAG, "CabinetFragment onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "CabinetFragment onViewCreating")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[ModifyUserDataViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val token = "Bearer ${getTokenFromLocalStorageUseCase().accessToken}"


        genderGroupListener()

        binding.saveButton.setOnClickListener {
            getCurrentUserId(token)
            updateUserInDataBase()
            updateUserInBackend(token)
            moveIntoCabinet()
        }

        Log.d(TAG, "CabinetFragment onViewCreated")
    }

    private fun genderGroupListener() {
        binding.genderGroup.setOnCheckedChangeListener { group, checkedId ->
            val gender = when (checkedId) {
                R.id.maleRadioButton -> Gender.MALE
                R.id.femaleRadioButton -> Gender.FEMALE
                else -> null
            }
            viewModel.gender.set(gender)
        }
    }

    private fun moveIntoCabinet() {
        lifecycleScope.launch(Dispatchers.Main) {
            findNavController().navigate(R.id.action_modifyUserDataFragment_to_navigation_cabinet)
        }
    }

    private fun updateUserInBackend(token: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val editUserItem = EditUserItem(
                about = binding.aboutEditText.text.toString(),
                birthdayDate = null,
                firstname = binding.firstNameEditText.text.toString(),
                gender = viewModel.gender.get(),
                lastname = binding.lastNameEditText.text.toString(),
                phone = binding.phoneEditText.text.toString(),
            )
            Log.d(TAG, editUserItem.toString())
            viewModel.updateUserByToken(token, editUserItem)
        }
    }

    private fun updateUserInDataBase() {
        lifecycleScope.launch(Dispatchers.IO) {
            val currentUser = viewModel.getUser(currentUserId)
            val userItem: UserItem? = currentUser?.let { userItem ->
                UserItem(
                    userId = userItem.userId,
                    email = userItem.email,
                    firstname = binding.firstNameEditText.text.toString(),
                    lastname = binding.lastNameEditText.text.toString(),
                    about = binding.aboutEditText.text.toString(),
                    gender = userItem.gender,
                    birthdayDate = userItem.birthdayDate,
                    phone = binding.phoneEditText.text.toString(),
                    photos = userItem.photos,
                )
            }
            userItem?.let { viewModel.updateUser(it) }
        }
    }

    private fun getCurrentUserId(token: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            currentUserId = viewModel.getUserByToken(token).userId
        }
    }

    companion object {
        const val TAG = "ModifyUserDataFragment"
    }
}