package ru.spbstu.mobileapplication.presentation.interview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
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

        val navController = findNavController(R.id.fragmentBottomBar)
    }
}