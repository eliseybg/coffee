package com.breaktime.madcoffeego.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.breaktime.madcoffeego.R
import com.breaktime.madcoffeego.api.RetrofitInstance
import com.breaktime.madcoffeego.api.body.SpecialDealsItem
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await


class HomeFragment : Fragment() {
    private var spacialDeals: List<SpecialDealsItem>? = null
    private var horizontalItems: LinearLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        horizontalItems = view?.findViewById(R.id.horizontal_items)

        lifecycleScope.launch(Dispatchers.IO) {
            spacialDeals = RetrofitInstance.API.getSpecialDeals().await()
            withContext(Dispatchers.Main) {
                spacialDeals!!.forEach { deal ->
                    val item = inflater.inflate(
                        R.layout.special_deal_item,
                        container,
                        false
                    ) as ConstraintLayout
                    val image = item.findViewById<ImageView>(R.id.image)
                    Picasso.get().load(deal.image).into(image)
                    val name = item.findViewById<TextView>(R.id.name)
                    name.text = deal.text
                    val discount = item.findViewById<TextView>(R.id.discount)
                    discount.text = deal.count
                    horizontalItems!!.addView(item)
                }
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}