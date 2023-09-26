package com.example.gatherhall

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.gatherhall.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Getting The Host Fragment And Initializing navController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

//        val w = window
//        w.setFlags(
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        )
//        w.statusBarColor = ContextCompat.getColor(applicationContext, R.color.logInHint)

    }

    //Handling The BackPress
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        navController.currentDestination?.id?.let { navId ->
            when (navId) {
                R.id.selectLanguageFragment -> {
                    finishAffinity()
                }
                R.id.forgotPasswordFragment -> {
                    navController.popBackStack()
                }
                R.id.OTPVerificationFragment -> {
                    navController.popBackStack()
                }
                R.id.resetPasswordFragment2 -> {
                    navController.popBackStack()
                }
                R.id.signUpFragment -> {
                    navController.popBackStack()
                }
                else -> {}
            }
        }

    }
}