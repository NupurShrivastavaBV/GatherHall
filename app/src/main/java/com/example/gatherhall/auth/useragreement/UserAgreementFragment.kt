package com.example.gatherhall.auth.useragreement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.gatherhall.R
import com.example.gatherhall.databinding.FragmentUserAgreementBinding
import com.example.gatherhall.helper.Constant
import com.example.gatherhall.helper.PrefHelper
import com.google.android.material.snackbar.Snackbar

class UserAgreementFragment : Fragment() {

    private var _binding: FragmentUserAgreementBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefHelper: PrefHelper
    private lateinit var progressBar: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserAgreementBinding.inflate(inflater, container, false)
        prefHelper = PrefHelper(requireContext())
        progressBar = binding.progressBar

        progressBar.visibility = View.VISIBLE
        initWebView()
        initNavigate()

        return binding.root
    }

    private fun initWebView(){
        val baseUrl : String? = null
        binding.webViewEndUserAgreement.loadDataWithBaseURL(baseUrl,Constant.htmlText,"text/html","UTF-8",null)
        progressBar.visibility = View.GONE
    }

    private fun initNavigate(){
        binding.btnContinueEndUser.setOnClickListener {
            if (binding.checkBoxEndUser.isChecked) {
                prefHelper.putBoolean(Constant.PREF_USER_AGREE,true)
                findNavController().navigate(R.id.action_userAgreementFragment_to_selectLanguageFragment)
            } else {
                val snackBar = Snackbar.make(
                    requireView(),
                    Constant.agreeTC,
                    Snackbar.LENGTH_SHORT
                )
                snackBar.setBackgroundTint(ContextCompat.getColor(requireContext(),R.color.white))
                snackBar.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
                snackBar.show()
            }
        }
    }
}