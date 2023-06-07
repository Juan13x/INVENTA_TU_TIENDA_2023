package com.example.inventa_tu_tienda_2023.ui.profile.no_user.sign_up

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.inventa_tu_tienda_2023.R
import com.example.inventa_tu_tienda_2023.databinding.FragmentSignUpBinding
import com.example.inventa_tu_tienda_2023.models.Register.SignUpData
import com.example.inventa_tu_tienda_2023.ui.shared.SharedViewModel
import com.example.myanimeapp.models.errors.e_Error
import com.google.android.material.snackbar.Snackbar

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var model: SignUpViewModel
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var sharedModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        model = ViewModelProvider(this)[SignUpViewModel::class.java]
        sharedModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val navController = findNavController()

        with(binding){
            signUpBackButton.setOnClickListener{
                val action = SignUpFragmentDirections.actionSignUpFragmentToNoUserMainFragment()
                navController.popBackStack()
            }

            signUpRedirectTextView.setOnClickListener {
                val action = SignUpFragmentDirections.actionSignUpFragmentToLogInFragment()
                navController.navigate(action)
            }

            signUpSubmitButton.setOnClickListener {
                val signUpData: SignUpData = SignUpData(
                    signUpEmailTextInputEditText.text.toString(),
                    signUpPasswordTextInputEditText.text.toString(),
                    signUpRepeatPasswordTextInputEditText.text.toString(),
                    signUpCellphoneNumberTextInputEditText.text.toString(),
                    signUpCompanyTextInputEditText.text.toString(),
                    signUpUserNameTextInputEditText.text.toString(),
                )
                model.createUser(signUpData)
            }

            model.successLiveData.observe(this@SignUpFragment){
                    user ->
                sharedModel.user = user
                val action = SignUpFragmentDirections.actionSignUpFragmentToProfileMainFragment()
                navController.navigate(action)
            }

            model.errorLiveData.observe(this@SignUpFragment){
                    errorData ->
                when(errorData.errorEvent){
                    e_Error.Email -> {
                        signUpEmailTextInputEditText.error = getString(errorData.errorMessageInt!!)
                    }
                    e_Error.Password -> {
                        signUpPasswordTextInputEditText.error = getString(errorData.errorMessageInt!!)
                    }
                    e_Error.RepPassword -> {
                        signUpRepeatPasswordTextInputEditText.error = getString(errorData.errorMessageInt!!)
                    }
                    e_Error.CompanyName -> {
                        signUpCompanyTextInputEditText.error = getString(errorData.errorMessageInt!!)
                    }
                    e_Error.GenericInt -> {
                        Snackbar.make(signUpCoordinatorLayout, errorData.errorMessageInt!!, Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(resources.getColor(R.color.Red))
                            .setTextColor(resources.getColor(R.color.White))
                            .show()
                    }
                    else -> {
                        Snackbar.make(signUpCoordinatorLayout, errorData.errorMessageStr!!, Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(resources.getColor(R.color.Red))
                            .setTextColor(resources.getColor(R.color.White))
                            .show()
                    }
                }
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