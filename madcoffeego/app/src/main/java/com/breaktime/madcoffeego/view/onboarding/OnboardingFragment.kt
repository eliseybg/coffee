package com.breaktime.madcoffeego.view.onboarding

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.breaktime.madcoffeego.R
import kotlin.math.abs


class OnboardingFragment : Fragment() {

    lateinit var onBoarding1: View
    lateinit var onBoarding2: View
    lateinit var onBoarding3: View
    lateinit var onBoarding4: View
    lateinit var onBoardingViewPager: ViewPager2

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)
        onBoarding1 = view.findViewById(R.id.on_boarding1)
        onBoarding2 = view.findViewById(R.id.on_boarding2)
        onBoarding3 = view.findViewById(R.id.on_boarding3)
        onBoarding4 = view.findViewById(R.id.on_boarding4)

        val skip = view.findViewById<TextView>(R.id.skip)

        skip.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        val next = view.findViewById<Button>(R.id.next)

        next.setOnClickListener {
            onBoardingViewPager.setCurrentItem(onBoardingViewPager.currentItem + 1, true)
        }

        onBoardingViewPager = view.findViewById(R.id.on_boarding_view_pager)
        var fromX = 0f
        var toX = 0f
        onBoardingViewPager.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> fromX = event.x
                MotionEvent.ACTION_UP -> {
                    toX = event.x
                    val deltaX = toX - fromX
                    if (deltaX > 0 && abs(deltaX) > 150) {
                        println("true")
                        return@setOnTouchListener  true
                    } else {
                       return@setOnTouchListener  false
                    }
                }
            }
            false

        }

        val onBoardingAdapter = OnBoardingAdapter(this, 4)
        onBoardingViewPager.adapter = onBoardingAdapter
        onBoardingViewPager.registerOnPageChangeCallback(onBoardingPageChangeCallback)
        return view
    }


    private var onBoardingPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            updateCircleMarker(position)
        }
    }

    private fun updateCircleMarker(position: Int) {
        onBoarding1.background = resources.getDrawable(R.drawable.onboarding_off_circle)
        onBoarding2.background = resources.getDrawable(R.drawable.onboarding_off_circle)
        onBoarding3.background = resources.getDrawable(R.drawable.onboarding_off_circle)
        onBoarding4.background = resources.getDrawable(R.drawable.onboarding_off_circle)
        when (position) {
            0 -> {
                onBoarding1.background =
                    resources.getDrawable(R.drawable.onboarding_on_circle)
            }
            1 -> {
                onBoarding2.background =
                    resources.getDrawable(R.drawable.onboarding_on_circle)
            }
            2 -> {
                onBoarding3.background =
                    resources.getDrawable(R.drawable.onboarding_on_circle)
            }
            3 -> {
                onBoarding4.background =
                    resources.getDrawable(R.drawable.onboarding_on_circle)
            }
        }
    }

    override fun onDestroy() {
        onBoardingViewPager.unregisterOnPageChangeCallback(onBoardingPageChangeCallback)
        super.onDestroy()
    }
}