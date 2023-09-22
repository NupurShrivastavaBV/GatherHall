package com.example.gatherhall.auth.forgotpassword

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.navigation.fragment.findNavController
import com.example.gatherhall.R
import com.example.gatherhall.databinding.FragmentForgotPasswordBinding
import com.example.gatherhall.databinding.FragmentSignUpBinding
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
        binding.btnForgotPassword.btnCustom.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_OTPVerificationFragment)
        }

        binding.toolbarForgotPassword.imgBackButton.setOnClickListener {
            findNavController().popBackStack()
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
        binding.txtEmail.inputLayout.hint = Constant.otpLabel
        binding.txtEmail.inputLayout.setHintStyle(R.font.poppins_regular)

    }

}