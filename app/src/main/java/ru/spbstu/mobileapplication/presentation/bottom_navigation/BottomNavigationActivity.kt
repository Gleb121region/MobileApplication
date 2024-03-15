package ru.spbstu.mobileapplication.presentation.bottom_navigation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.ActivityBottomNavigationBinding
import ru.spbstu.mobileapplication.domain.survey_answers.entity.SurveyResult

@AndroidEntryPoint
class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigationView

        val navController = findNavController(R.id.fragmentBottomBar)
        Log.d(TAG, "onCreate")
        navView.setupWithNavController(navController)

    }

    companion object {
        private const val TAG = "BottomNav"
    }
}
