package com.example.gatherhall.auth.signup

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import androidx.navigation.fragment.findNavController
import com.example.gatherhall.R
import com.example.gatherhall.databinding.FragmentSignUpBinding
import com.example.gatherhall.helper.Constant
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        initNavigate()
        initXmlValues()
        initSpannableText()
        binding.btnRegister.btnCustom.setOnClickListener {
            validateAndRegister()
        }


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

    //Spannable Text For Checkbox Text
    private fun initSpannableText(){
        val txtSpanColor = ContextCompat.getColor(requireContext(),
            R.color.purple
        )

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val webpage = Uri.parse(" ")
                val intent = Intent(Intent.ACTION_VIEW, webpage)
                startActivity(intent)
            }
        }

        val spannableStringBuilder = SpannableStringBuilder()

        val checkboxText = SpannableString("I agree with the ")
        val checkboxNextText = SpannableString("Terms and Condition ")
        val checkboxAndText = SpannableString("\nand ")
        val checkboxSpanText = SpannableString("Privacy Policy")

        checkboxNextText.setSpan(clickableSpan, 0, checkboxNextText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        checkboxNextText.setSpan(UnderlineSpan(), 0, checkboxNextText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        checkboxNextText.setSpan(ForegroundColorSpan(txtSpanColor), 0, checkboxNextText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        checkboxSpanText.setSpan(clickableSpan, 0, checkboxSpanText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        checkboxSpanText.setSpan(UnderlineSpan(), 0, checkboxSpanText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        checkboxSpanText.setSpan(ForegroundColorSpan(txtSpanColor), 0, checkboxSpanText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        spannableStringBuilder.append(checkboxText)
        spannableStringBuilder.append(checkboxNextText)
        spannableStringBuilder.append(checkboxAndText)
        spannableStringBuilder.append(checkboxSpanText)


        binding.txtTerms.text = spannableStringBuilder
        binding.txtTerms.movementMethod = LinkMovementMethod.getInstance()
    }


    private fun initXmlValues() {
        binding.btnRegister.btnCustom.text = Constant.register
        binding.toolbarRegisterScreen.txtToolbarTitle.text = Constant.register
        binding.toolbarRegisterScreen.imgBackButton.visibility = View.VISIBLE
        binding.txtUserName.inputLayout.hint = Constant.name
        binding.txtUserName.inputLayout.setHintStyle(R.font.poppins_regular)
        binding.txtUserEmail.inputLayout.hint = Constant.email
        binding.txtUserEmail.inputLayout.setHintStyle(R.font.poppins_regular)
        binding.txtUserPassword.inputLayout.hint = Constant.password
        binding.txtUserPassword.inputLayout.setHintStyle(R.font.poppins_regular)
    }


    private fun validateAndRegister() {
        val name = binding.txtUserName.editText.text.toString().trim()
        val email = binding.txtUserEmail.editText.text.toString().trim()
        val mobile = binding.etPhoneNumber.text.toString().trim()
        val password = binding.txtUserPassword.editText.text.toString().trim()

        clearError()

        if (name.isEmpty()) {
            binding.txtUserName.inputLayout.error = Constant.nameEmptyError
            binding.txtUserName.inputLayout.requestFocus()
            textWatcher(binding.txtUserName.editText)
        }

        else if(!isValidName(name)){
            binding.txtUserName.inputLayout.error = Constant.nameError
            binding.txtUserName.inputLayout.requestFocus()
            textWatcher(binding.txtUserName.editText)
        }

        else if (email.isEmpty()) {
            binding.txtUserEmail.inputLayout.error = Constant.emailEmptyError
            binding.txtUserEmail.inputLayout.requestFocus()
            textWatcher(binding.txtUserEmail.editText)
        }

        else if (!isValidEmail(email)) {
            binding.txtUserEmail.inputLayout.error = Constant.emailError
            binding.txtUserEmail.inputLayout.requestFocus()
            textWatcher(binding.txtUserEmail.editText)
        }


        else if (mobile.isEmpty()) {
            binding.etPhoneNumber.error = Constant.mobileEmptyError
            binding.etPhoneNumber.requestFocus()
            textWatcher(binding.etPhoneNumber)
        }

        else if (!isValidPhoneNumber(mobile)) {
            binding.etPhoneNumber.error  = Constant.mobileError
            binding.etPhoneNumber.requestFocus()
            textWatcher(binding.etPhoneNumber)
        }

        else if (password.isEmpty()) {
            binding.txtUserPassword.inputLayout.error = Constant.passwordEmptyError
            binding.txtUserPassword.inputLayout.requestFocus()
            textWatcher(binding.txtUserPassword.editText)
        }

        else if (password.length < 6) {
            binding.txtUserPassword.inputLayout.error = Constant.passwordError
            binding.txtUserPassword.inputLayout.requestFocus()
            textWatcher(binding.txtUserPassword.editText)
        }

        else if (!binding.checkBoxTerms.isChecked) {
            val snackBar = Snackbar.make(
                requireView(),
                Constant.agreeTC,
                Snackbar.LENGTH_SHORT
            )
            snackBar.setBackgroundTint(ContextCompat.getColor(requireContext(),R.color.white))
            snackBar.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
            snackBar.show()
        }
        else{
            Toast.makeText(context, "User registered", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearError() {
        binding.txtUserName.inputLayout.error = null
        binding.txtUserEmail.inputLayout.error = null
        binding.etPhoneNumber.error = null
        binding.txtUserPassword.inputLayout.error = null
    }


    // Function to validate email
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Function to validate phone number
    private fun isValidPhoneNumber(phone: String): Boolean {
        return phone.matches(Regex("^\\d{8,14}$"))
    }

    private fun isValidName(name: String): Boolean {
        val pattern = Pattern.compile("^[a-zA-Z]+$")
        return pattern.matcher(name).matches()
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