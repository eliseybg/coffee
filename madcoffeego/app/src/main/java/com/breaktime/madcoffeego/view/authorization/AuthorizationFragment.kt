package com.breaktime.madcoffeego.view.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.breaktime.madcoffeego.R
import com.breaktime.madcoffeego.api.RetrofitInstance
import com.breaktime.madcoffeego.data.LocalStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class AuthorizationFragment : Fragment() {
    private lateinit var phoneCodeTitle: TextView
    private lateinit var phoneCodeText: EditText
    private lateinit var agreementCheckBox: CheckBox
    private lateinit var agreementLinearLayout: LinearLayout
    private lateinit var continueSigninBtn: Button
    private var phoneNumber = ""

    private var isCode = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_authorization, container, false)
        phoneCodeTitle = view.findViewById(R.id.phone_code)
        phoneCodeText = view.findViewById(R.id.phone_code_text)
        agreementCheckBox = view.findViewById(R.id.agreement_cb)
        continueSigninBtn = view.findViewById(R.id.continue_signin)
        agreementLinearLayout = view.findViewById(R.id.agreement_linear_layout)

        phoneCodeText.doOnTextChanged { text, start, before, count ->
            if (isCode && !text.isNullOrEmpty()) {
                continueSigninBtn.isFocusable = true
                continueSigninBtn.isEnabled = true
            } else if (!text.isNullOrEmpty() && agreementCheckBox.isChecked) {
                continueSigninBtn.isFocusable = true
                continueSigninBtn.isEnabled = true
            } else {
                continueSigninBtn.isFocusable = false
                continueSigninBtn.isEnabled = true
            }
        }
        agreementCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (phoneCodeText.text.isNotEmpty()) {
                    continueSigninBtn.isFocusable = true
                    continueSigninBtn.isEnabled = true
                } else {
                    continueSigninBtn.isFocusable = false
                    continueSigninBtn.isEnabled = false
                }
            }
        }
        continueSigninBtn.setOnClickListener {
            if (isCode) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val localStorage = LocalStorage(requireContext())
                    val uuid = localStorage.uuid
                    val call =
                        RetrofitInstance.API.checkPhoneKey(
                            phoneNumber,
                            phoneCodeText.text.toString(),
                            uuid
                        ).await()
                    localStorage.token = call.token.toString()
                    withContext(Dispatchers.Main) {
                        findNavController().popBackStack()
                        findNavController().navigate(R.id.homeFragment)
                    }
                }
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    phoneNumber = phoneCodeText.text.toString()
                    val call =
                        RetrofitInstance.API.getPhoneKey(phoneCodeText.text.toString()).await()
                    withContext(Dispatchers.Main) {
                        phoneCodeText.setText(call.phoneKey)
                    }
                }
                agreementLinearLayout.visibility = View.GONE
                phoneCodeTitle.text = "Code"
                phoneCodeText.text.clear()
                phoneCodeText.hint = "____________"
                continueSigninBtn.text = "Sign in"
                continueSigninBtn.isActivated = false
                continueSigninBtn.isEnabled = false
                isCode = true
            }
        }
        return view
    }
}