package com.breaktime.madcoffeego.view.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.breaktime.madcoffeego.R

class OnBoardingItem : Fragment() {

    companion object {
        private const val ARG_POSITION = "ARG_POSITION"

        fun getInstance(position: Int) = OnBoardingItem().apply {
            arguments = bundleOf(ARG_POSITION to position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.on_boarding_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val position = requireArguments().getInt(ARG_POSITION)
        val onBoardingTitles = arrayOf("Hello!", "Search for a coffee shop", "Making an order", "Receiving an order")
        val onBoardingTexts = arrayOf("Coffee to Go is an application in which you can order coffee online and pick it up at the coffee shop closest to you.\n" +
                "Now let's tell you how it works", "The map shows the nearest coffee shops to you, choose the most convenient one for you. The app will tell you how long to go to it.", "Choose your favorite drinks and desserts. You can change their composition and choose the time when it will be convenient for you to pick them up.", "At the specified time, come to the coffee shop and enjoy the taste of coffee, without queuing and waiting.")
        val onBoardingImages = arrayOf(
            R.drawable.onboarding1,
            R.drawable.onboarding2,
            R.drawable.onboarding3,
            R.drawable.onboarding4
        )

        view.findViewById<ImageView>(R.id.on_boarding_image)
            .setImageDrawable(resources.getDrawable(onBoardingImages[position]))
        view.findViewById<TextView>(R.id.on_boarding_title).text = onBoardingTitles[position]
        view.findViewById<TextView>(R.id.on_boarding_description).text = onBoardingTexts[position]
    }
}