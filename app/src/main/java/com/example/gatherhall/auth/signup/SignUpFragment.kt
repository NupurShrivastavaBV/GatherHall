package com.example.gatherhall.auth.signup

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.navigation.fragment.findNavController
import com.example.gatherhall.R
import com.example.gatherhall.databinding.FragmentSignUpBinding
import com.example.gatherhall.helper.Constant
import com.google.android.material.textfield.TextInputLayout

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        initNavigate()
        initXmlValues()


        return binding.root

    }

    private fun initNavigate() {
        binding.txtLogIn.setOnClickListener {
            findNavController().navigate(R.id.logInFragment)
        }
        binding.toolbarRegisterScreen.imgBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun TextInputLayout.setHintStyle(id: Int) {
        doOnLayout {
            setHintTextAppearance(id)
        }
    }

    private fun initXmlValues() {
        binding.btnRegister.btnCustom.text = Constant.register
        binding.toolbarRegisterScreen.txtToolbarTitle.text = Constant.register
        binding.txtUserName.inputLayout.hint = Constant.name
        binding.txtUserName.inputLayout.setHintStyle(R.font.poppins_regular)
        binding.txtUserEmail.inputLayout.hint = Constant.email
        binding.txtUserEmail.inputLayout.setHintStyle(R.font.poppins_regular)
        binding.txtUserPassword.inputLayout.hint = Constant.password
        binding.txtUserPassword.inputLayout.setHintStyle(R.font.poppins_regular)
        binding.txtUserPassword.editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        binding.txtUserPassword.inputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        binding.txtUserPassword.inputLayout.setEndIconDrawable(R.drawable.ic_password_toggle)

    }

}