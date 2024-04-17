package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.announcement

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.spbstu.mobileapplication.R

class AnnouncementDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = AnnouncementDetailsFragment()
    }

    private val viewModel: AnnouncementDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_announcement_details, container, false)
    }
}