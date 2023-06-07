package com.example.inventa_tu_tienda_2023.ui.profile.no_user.log_in

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.inventa_tu_tienda_2023.R
import com.example.inventa_tu_tienda_2023.databinding.FragmentLogInBinding
import com.example.inventa_tu_tienda_2023.ui.profile.no_user.sign_up.SignUpFragmentDirections
import com.example.inventa_tu_tienda_2023.ui.shared.SharedViewModel
import com.example.myanimeapp.models.errors.e_Error
import com.google.android.material.snackbar.Snackbar

class LogInFragment : Fragment() {

    companion object {
        fun newInstance() = LogInFragment()
    }

    private lateinit var model: LogInViewModel
    private lateinit var binding: FragmentLogInBinding
    private lateinit var sharedModel: SharedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLogInBinding.inflate(layoutInflater)
        model = ViewModelProvider(this)[LogInViewModel::class.java]
        sharedModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val navController = findNavController()

        with(binding){
            model.successLiveData.observe(this@LogInFragment){
                    user ->
                sharedModel.user = user
                val action = LogInFragmentDirections.actionLogInFragmentToProfileMainFragment()
                navController.navigate(action)
            }

            logInEnterButton.setOnClickListener {
                val email = logInEmailTextInputEditText.text.toString()
                val password = logInPasswordTextInputEditText.text.toString()
                model.login(email, password)
            }

            model.errorLiveData.observe(this@LogInFragment){
                    errorData ->
                when(errorData.errorEvent){
                    e_Error.Email -> {
                        logInEmailTextInputEditText.error = getString(errorData.errorMessageInt!!)
                    }
                    e_Error.Password -> {
                        logInPasswordTextInputEditText.error = getString(errorData.errorMessageInt!!)
                    }
                    e_Error.GenericInt -> {
                        Snackbar.make(loginCoordinatorLayout, errorData.errorMessageInt!!, Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(resources.getColor(R.color.Red))
                            .setTextColor(resources.getColor(R.color.White))
                            .show()
                    }
                    else -> {
                        Snackbar.make(loginCoordinatorLayout, errorData.errorMessageStr!!, Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(resources.getColor(R.color.Red))
                            .setTextColor(resources.getColor(R.color.White))
                            .show()
                    }
                }
            }

            logInBackButton.setOnClickListener{
                navController.popBackStack()
            }

            logInNotRegisteredTextView.setOnClickListener {
                val action = LogInFragmentDirections.actionLogInFragmentToSignUpFragment()
                navController.navigate(action)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}