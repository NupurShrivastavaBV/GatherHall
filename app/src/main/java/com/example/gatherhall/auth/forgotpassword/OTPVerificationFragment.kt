package com.example.gatherhall.auth.forgotpassword

import android.graphics.ColorSpace.match
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.doOnLayout
import androidx.navigation.fragment.findNavController
import com.example.gatherhall.R
import com.example.gatherhall.databinding.FragmentForgotPasswordBinding
import com.example.gatherhall.databinding.FragmentOTPVerificationBinding
import com.example.gatherhall.helper.Constant
import com.google.android.material.textfield.TextInputLayout


class OTPVerificationFragment : Fragment() {

    private var _binding: FragmentOTPVerificationBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOTPVerificationBinding.inflate(inflater, container, false)

        initNavigation()
        initXmlValues()

        return binding.root
    }

    private fun initNavigation() {
        binding.btnOTPVerify.btnCustom.setOnClickListener {
            findNavController().navigate(R.id.action_OTPVerificationFragment_to_resetPasswordFragment2)
        }
        binding.toolbarOTPValidation.imgBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun TextInputLayout.setHintStyle(id: Int) {
        doOnLayout {
            setHintTextAppearance(id)
        }
    }

    private fun initXmlValues() {
        binding.btnOTPVerify.btnCustom.text = Constant.verify
        binding.toolbarOTPValidation.txtToolbarTitle.text = Constant.otpVerification
        binding.toolbarOTPValidation.imgBackButton.visibility = View.VISIBLE
        binding.txtOTPCode.inputLayout.hint = Constant.otpLabel
        binding.txtOTPCode.inputLayout.setHintStyle(R.font.poppins_regular)

    }

    private fun validateAndNavigate(){
        val etOtp = binding.txtOTPCode.editText.text.toString().trim()
        if (etOtp.matches(Regex("^[0-9]{6}$"))){
            binding.btnOTPVerify.btnCustom.setOnClickListener {
                findNavController().navigate(R.id.action_OTPVerificationFragment_to_resetPasswordFragment2)
            }
        }
        else{
            binding.txtOTPCode.inputLayout.error = Constant.otpErrorMsg
            binding.txtOTPCode.inputLayout.requestFocus()
            textWatcher(binding.txtOTPCode.editText)
        }
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

    fun clearError() {
        binding.txtOTPCode.inputLayout.error = null
    }

}