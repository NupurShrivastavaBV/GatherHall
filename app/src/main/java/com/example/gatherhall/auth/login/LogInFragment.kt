package com.example.gatherhall.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
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
    ): View? {
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
    private fun initXmlValues(){
        binding.btnLogIn.btnCustom.text = Constant.logIn
        binding.toolbarSignInScreen.txtToolbarTitle.text = Constant.logIn
//        binding.tilMobile.setHintStyle(R.font.poppins_regular)
//        binding.tilPassword.setHintStyle(R.font.poppins_regular)
    }

    //handling the navigation
    private fun initNavigation(){
        binding.toolbarSignInScreen.imgBackButton.setOnClickListener {
            requireActivity().finishAffinity()
        }
    }
}