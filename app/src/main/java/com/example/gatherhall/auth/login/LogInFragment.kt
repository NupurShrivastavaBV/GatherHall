package com.example.gatherhall.auth.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
        binding.txtPassword.editText.imeOptions = EditorInfo.IME_ACTION_DONE
    }

    //handling the navigation
    private fun initNavigation() {

        binding.btnLogIn.btnCustom.setOnClickListener {
            validateAndNavigate()
        }

        binding.txtSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_signUpFragment)
        }

        binding.txtForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_forgotPasswordFragment)
        }
    }

    // Function to validate email
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Function to validate phone number
    private fun isValidPhoneNumber(phone: String): Boolean {
        return phone.matches(Regex("^\\d{8,14}$"))
    }


    // Function to validate and navigate
    private fun validateAndNavigate() {
        val etUserCred = binding.txtEmail.editText.text.toString().trim()
        val etUserPassword = binding.txtPassword.editText.text.toString().trim()

        clearError()

        if (etUserCred.isEmpty()) {
            binding.txtEmail.inputLayout.error = Constant.UserCredEmptyError
            binding.txtEmail.inputLayout.requestFocus()
            textWatcher(binding.txtEmail.editText)
            return
        } else if (!isValidEmail(etUserCred) && !isValidPhoneNumber(etUserCred)) {
            binding.txtEmail.inputLayout.error = Constant.UserCredError
            binding.txtEmail.inputLayout.requestFocus()
            textWatcher(binding.txtEmail.editText)
            return
        }

        if (etUserPassword.isEmpty()) {
            binding.txtPassword.inputLayout.error = Constant.PasswordError
            binding.txtPassword.inputLayout.requestFocus()
            textWatcher(binding.txtPassword.editText)
            return
        } else if (etUserPassword.length < 6) {
            binding.txtPassword.inputLayout.error = Constant.PasswordLengthError
            binding.txtPassword.inputLayout.requestFocus()
            textWatcher(binding.txtPassword.editText)
            return
        }

        // If all validations pass, you can navigate or perform other actions here
        Toast.makeText(requireContext(), Constant.loggedIn, Toast.LENGTH_LONG).show()
    }

    // Function to clear error messages
     fun clearError() {
        binding.txtEmail.inputLayout.error = null
        binding.txtPassword.inputLayout.error = null
    }

     private fun textWatcher(view: EditText) {
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clearError()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}