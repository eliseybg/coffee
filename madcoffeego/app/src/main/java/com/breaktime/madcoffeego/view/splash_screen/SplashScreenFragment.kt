package com.breaktime.madcoffeego.view.splash_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.breaktime.madcoffeego.R
import com.breaktime.madcoffeego.data.LocalStorage
import kotlinx.coroutines.*

class SplashScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        GlobalScope.launch {
            delay(2000)
            val storage = LocalStorage(requireContext())
            withContext(Dispatchers.Main) {
                if (storage.token == null) {
                    findNavController().popBackStack()
                    findNavController().navigate(R.id.onboardingFragment)
                }
                else {
                    findNavController().popBackStack()
                    findNavController().navigate(R.id.homeFragment)
                }
            }
        }
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }
}