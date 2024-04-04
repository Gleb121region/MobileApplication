package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.cabinet

import android.app.AlertDialog
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
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.FragmentCabinetBinding
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.GetTokenFromLocalStorageUseCase
import ru.spbstu.mobileapplication.domain.user.entity.UserItem
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class CabinetFragment : Fragment() {

    private lateinit var viewModel: CabinetViewModel

    @Inject
    lateinit var getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase

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

        val token = "Bearer ${getTokenFromLocalStorageUseCase().accessToken}"

        loadUserByTokenFromNet(token)

        recordUserIntoDatabase()

        loadUserInfoIntoTextViews()

        binding.updateButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_cabinet_to_modifyUserDataFragment)
        }

        binding.logoutButton.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes") { _, _ ->
                    findNavController().navigate(R.id.action_navigation_cabinet_to_mainActivity)
                }
                .setNegativeButton("No", null)
                .show()
        }

        Log.d(TAG, "CabinetFragment onViewCreated")
    }

    private fun loadUserByTokenFromNet(token: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                viewModel.getUserByToken(token)
            } catch (e: Exception) {
                Log.e(TAG, "Error loading user by token", e)
            }
        }
    }

    private fun recordUserIntoDatabase() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.currentUser.value?.let { viewModel.insertUser(it) }
        }
    }

    private fun loadUserInfoIntoTextViews() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.currentUser.observe(viewLifecycleOwner) {
                loadingImageIntoImageView(it.photos, it.imagePosition)
                loadingUserInfo(it)
                loadingLessImportantUserInfo(it)
            }
        }
    }

    private fun loadingLessImportantUserInfo(it: UserItem) {
        if (it.phone != null) {
            binding.profilePhone.text = it.phone
        } else {
            binding.profileAbout.visibility = View.INVISIBLE
        }
        if (it.about != null) {
            binding.profileAbout.text = it.about
        } else {
            binding.profileAbout.visibility = View.INVISIBLE
        }
    }

    private fun loadingUserInfo(it: UserItem) {
        val fullName = resources.getString(
            R.string.profile_info,
            it.lastname ?: "",
            it.firstname,
            it.gender ?: ""
        )
        binding.profileFullNamePlusGenderPlusAge.text = fullName
        binding.profileEmail.text = it.email
    }

    private fun loadingImageIntoImageView(photos: List<String>?, imagePosition: Int) {
        if (photos?.isNotEmpty() == true) {
            Picasso.get().load(photos[imagePosition]).into(binding.profileImage)
        } else {
            Picasso.get().load(R.drawable.camera).into(binding.profileImage)
        }
    }


    private companion object {
        private const val TAG = "CabinetFragment"
    }
}
