package com.example.gatherhall.auth.language.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gatherhall.R
import com.example.gatherhall.auth.language.adapter.LanguageAdapter
import com.example.gatherhall.databinding.FragmentSelectLanguageBinding
import com.example.gatherhall.helper.Constant
import com.example.gatherhall.helper.ModelData

class SelectLanguageFragment : Fragment() {

    private var _binding: FragmentSelectLanguageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectLanguageBinding.inflate(inflater, container, false)

        initRecycleView()
        initNavigation()
        initXmlValues()

        return binding.root
    }

    //Setting recycleView adapter
    private fun initRecycleView(){
        val listOfLanguage = ModelData.getListOfLanguage()
        val rcvLanguage = binding.rcvLanguage
        rcvLanguage.adapter = LanguageAdapter(listOfLanguage,requireContext())
    }

    //Setting the values
    private fun initXmlValues(){
        binding.btnContinue.btnCustom.text = Constant.btnContinue
        binding.toolbarLanguageScreen.txtToolbarTitle.text = Constant.selectLanguage
    }

    //Handling Navigation
    private fun initNavigation(){
        binding.toolbarLanguageScreen.imgBackButton.setOnClickListener {
            requireActivity().finishAffinity()
        }

        binding.btnContinue.btnCustom.setOnClickListener {
            findNavController().navigate(R.id.action_selectLanguageFragment_to_logInFragment)
        }
    }
}