package com.example.gatherhall.util

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.textfield.TextInputLayout

class Validation {

         fun validateAndNavigate(editText: EditText, inputLayout: TextInputLayout,context: Context) {
            val etUserCred = editText.text.toString().trim()
            val etUserPassword = editText.text.toString().trim()

            clearError(inputLayout)

            if (etUserCred.isEmpty()) {
                inputLayout.error = "Please Enter Email or Phone No"
                inputLayout.requestFocus()
                textWatcher(editText,inputLayout)
                return
            } else if (!isValidEmail(etUserCred) && !isValidPhoneNumber(etUserCred)) {
                inputLayout.error = "Invalid Email or Phone No"
                inputLayout.requestFocus()
                textWatcher(editText,inputLayout)
                return
            }

            if (etUserPassword.isEmpty()) {
                inputLayout.error = "Please Enter Password"
                inputLayout.requestFocus()
                textWatcher(editText,inputLayout)
                return
            } else if (etUserPassword.length < 6) {
                inputLayout.error = "Password should be at least 6 characters long"
                inputLayout.requestFocus()
                textWatcher(editText,inputLayout)
                return
            }

            // If all validations pass, you can navigate or perform other actions here
            Toast.makeText(context, "Logged In", Toast.LENGTH_LONG).show()
        }

    private fun clearError(inputLayout: TextInputLayout) {
        inputLayout.error = null
    }

    private fun textWatcher(view: EditText,inputLayout: TextInputLayout) {
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clearError(inputLayout)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    // Function to validate email
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Function to validate phone number
    private fun isValidPhoneNumber(phone: String): Boolean {
        return phone.matches(Regex("^\\d{8,14}$"))
    }


}