package ru.spbstu.mobileapplication.presentation.authorization_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.spbstu.mobileapplication.databinding.ActivityMainBinding
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.authorization_activity.view_models.RestoreAccessViewModel
import ru.spbstu.mobileapplication.presentation.authorization_activity.view_models.SignInViewModel
import ru.spbstu.mobileapplication.presentation.authorization_activity.view_models.SignUpViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var signInViewModel: SignInViewModel
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var restoreAccessViewModel: RestoreAccessViewModel

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as App).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}