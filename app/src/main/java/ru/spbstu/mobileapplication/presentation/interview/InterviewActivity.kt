package ru.spbstu.mobileapplication.presentation.interview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.ActivityInterviewBinding

@AndroidEntryPoint
class InterviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInterviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInterviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentInterview) as NavHostFragment

        val navController = navHostFragment.navController
    }
}