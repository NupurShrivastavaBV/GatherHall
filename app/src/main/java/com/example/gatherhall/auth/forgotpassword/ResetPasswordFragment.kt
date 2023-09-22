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
import com.example.gatherhall.databinding.FragmentResetPasswordBinding
import com.example.gatherhall.helper.Constant
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ResetPasswordFragment : Fragment() {

    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResetPasswordBinding.inflate(inflater, container, false)

        initTextValues(binding.txtNewPassword.inputLayout,binding.txtNewPassword.editText,Constant.newPassword)
        initTextValues(binding.txtConfirmNewPassword.inputLayout,binding.txtConfirmNewPassword.editText,Constant.confirmNewPassword)
        initXMLValues()


        return binding.root
    }

    private fun initTextValues(viewLayout: TextInputLayout, viewEdit: TextInputEditText, hintText : String){
        viewLayout.hint = hintText
        viewLayout.setHintStyle(R.font.poppins_regular)
        viewEdit.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        viewLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        viewLayout.setEndIconDrawable(R.drawable.ic_password_toggle)
    }

    private fun initXMLValues(){
        binding.btnResetPassword.btnCustom.text = Constant.reset
        binding.toolbarResetPassword.txtToolbarTitle.text = Constant.logIn
    }

    private fun TextInputLayout.setHintStyle(id: Int) {
        doOnLayout {
            setHintTextAppearance(id)
        }
    }

    private fun initNavigate(){
        binding.btnResetPassword.btnCustom.setOnClickListener {
            findNavController().navigate(R.id.action_resetPasswordFragment2_to_changePasswordFragment)
        }
    }

}