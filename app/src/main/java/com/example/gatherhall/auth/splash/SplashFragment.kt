package com.example.gatherhall.auth.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gatherhall.R
import com.example.gatherhall.databinding.FragmentSplashBinding
import com.example.gatherhall.helper.Constant
import com.example.gatherhall.helper.PrefHelper


class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefHelper: PrefHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        prefHelper = PrefHelper(requireContext())

        initSplash()

        return binding.root

    }

    //using handler to handle splash screen
    private fun initSplash(){
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            if(!prefHelper.getBoolean(Constant.PREF_USER_AGREE)){
                findNavController().navigate(R.id.action_splashFragment_to_userAgreementFragment)
            }
            else{
                findNavController().navigate(R.id.action_splashFragment_to_selectLanguageFragment)
            }
        },2000)
    }
}