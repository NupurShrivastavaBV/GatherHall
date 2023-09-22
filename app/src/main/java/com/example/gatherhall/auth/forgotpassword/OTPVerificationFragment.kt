package com.example.gatherhall.auth.forgotpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    ): View? {

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
        binding.txtOTPCode.inputLayout.hint = Constant.mobileNumberEmailIid
        binding.txtOTPCode.inputLayout.setHintStyle(R.font.poppins_regular)

    }

}