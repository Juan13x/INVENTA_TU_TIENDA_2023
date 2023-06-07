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

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var viewModel: SignUpViewModel
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        val navController = findNavController()

        with(binding){
            signUpBackButton.setOnClickListener{
                val action = SignUpFragmentDirections.actionSignUpFragmentToNoUserMainFragment()
                navController.navigate(action)
            }
        }
        return binding.root
    }

}