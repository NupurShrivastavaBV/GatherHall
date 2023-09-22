package com.example.gatherhall.auth.login

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import com.example.gatherhall.R
import com.example.gatherhall.databinding.FragmentLogInBinding
import com.example.gatherhall.helper.Constant
import com.google.android.material.textfield.TextInputLayout


class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)

        initXmlValues()
        initNavigation()
        binding.btnLogIn.btnCustom.setOnClickListener {
            validateAndNavigate()
        }

        return binding.root

    }

    //Setting font for hint text
    private fun TextInputLayout.setHintStyle(id: Int) {
        doOnLayout {
            setHintTextAppearance(id)
        }
    }


    //Setting the values
    private fun initXmlValues() {
        binding.btnLogIn.btnCustom.text = Constant.logIn
        binding.toolbarSignInScreen.txtToolbarTitle.text = Constant.logIn
        binding.txtEmail.inputLayout.hint = Constant.mobileNumberEmailIid
        binding.txtEmail.inputLayout.setHintStyle(R.font.poppins_regular)
        binding.txtPassword.inputLayout.hint = Constant.password
        binding.txtPassword.inputLayout.setHintStyle(R.font.poppins_regular)
        binding.txtPassword.editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        binding.txtPassword.inputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        binding.txtPassword.inputLayout.setEndIconDrawable(R.drawable.ic_password_toggle)

    }

    //handling the navigation
    private fun initNavigation() {
        binding.toolbarSignInScreen.imgBackButton.setOnClickListener {
            requireActivity().finishAffinity()
        }
    }

    // Function to validate email
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Function to validate phone number
    private fun isValidPhoneNumber(phone: String): Boolean {
        return Patterns.PHONE.matcher(phone).matches()
    }


    //to remove the focus from error
    private fun clearError() {
        binding.txtEmail.inputLayout.error = null
        binding.txtPassword.inputLayout.error = null
    }


    private fun validateAndNavigate() {
        val etUserCred = binding.txtEmail.editText.text.toString()
        val etUserPassword = binding.txtPassword.editText.text.toString()

        clearError()

        if (!isValidEmail(etUserCred) && !isValidPhoneNumber(etUserCred)) {
            binding.txtEmail.inputLayout.error = "Invalid Email or Phone No"
            binding.txtEmail.inputLayout.requestFocus()
            binding.txtEmail.editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    clearError()
                }
                override fun afterTextChanged(s: Editable?) {}
            })
        }
        else if (etUserPassword.isEmpty() || etUserPassword.length < 6) {
            binding.txtPassword.inputLayout.error = "Password should be at least 6 characters long"
            binding.txtPassword.inputLayout.requestFocus()
            binding.txtPassword.editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    clearError()
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        } else {
            Toast.makeText(requireContext(), "Logged In", Toast.LENGTH_LONG).show()
        }
    }
}