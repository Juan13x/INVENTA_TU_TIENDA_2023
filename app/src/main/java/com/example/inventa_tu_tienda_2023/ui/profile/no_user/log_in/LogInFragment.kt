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

class LogInFragment : Fragment() {

    companion object {
        fun newInstance() = LogInFragment()
    }

    private lateinit var viewModel: LogInViewModel
    private lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LogInViewModel::class.java]

        val navController = findNavController()

        with(binding){
            logInBackButton.setOnClickListener{
                val action = LogInFragmentDirections.actionLogInFragmentToNoUserMainFragment()
                navController.navigate(action)
            }
        }
        return binding.root
    }

}