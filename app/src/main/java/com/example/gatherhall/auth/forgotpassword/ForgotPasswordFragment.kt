package com.example.gatherhall.auth.forgotpassword

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.gatherhall.MainActivity
import com.example.gatherhall.R
import com.example.gatherhall.databinding.FragmentForgotPasswordBinding
import com.example.gatherhall.helper.Constant
import com.google.android.material.textfield.TextInputLayout


class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)

        initXmlValues()
        initNavigation()


        return binding.root
    }

    private fun initNavigation() {
        binding.toolbarForgotPassword.imgBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnForgotPassword.btnCustom.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_OTPVerificationFragment)
        }

    }

    private fun TextInputLayout.setHintStyle(id: Int) {
        doOnLayout {
            setHintTextAppearance(id)
        }
    }

    private fun initXmlValues() {
        binding.btnForgotPassword.btnCustom.text = Constant.send
        binding.toolbarForgotPassword.txtToolbarTitle.text = Constant.forgotPassword
        binding.txtEmail.inputLayout.hint = Constant.mobileNumberEmailIid
        binding.txtEmail.inputLayout.setHintStyle(R.font.poppins_regular)
        binding.toolbarForgotPassword.imgBackButton.visibility = View.VISIBLE
    }

    private fun validateAndNavigate() {
        val etUserCred = binding.txtEmail.editText.text.toString().trim()


        clearError()

        if (etUserCred.isEmpty()) {
            clearError()
            binding.txtEmail.inputLayout.error = Constant.UserCredEmptyError
            binding.txtEmail.inputLayout.requestFocus()
            textWatcher(binding.txtEmail.editText)
            return
        } else if (!isValidEmail(etUserCred) && !isValidPhoneNumber(etUserCred)) {
            clearError()
            binding.txtEmail.inputLayout.error = Constant.UserCredError
            binding.txtEmail.inputLayout.requestFocus()
            textWatcher(binding.txtEmail.editText)
            return
        }
        else{
            binding.btnForgotPassword.btnCustom.setOnClickListener {
                findNavController().navigate(R.id.action_forgotPasswordFragment_to_OTPVerificationFragment)
            }
        }

    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPhoneNumber(phone: String): Boolean {
        return phone.matches(Regex("^\\d{8,14}$"))
    }

    fun clearError() {
        binding.txtEmail.inputLayout.error = null
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