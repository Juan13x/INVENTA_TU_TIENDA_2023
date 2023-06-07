package com.example.inventa_tu_tienda_2023.ui.profile.no_user.no_user_main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.inventa_tu_tienda_2023.R
import com.example.inventa_tu_tienda_2023.databinding.FragmentNoUserMainBinding
import com.google.android.material.snackbar.Snackbar


class NoUserMainFragment : Fragment() {

    companion object {
        fun newInstance() = NoUserMainFragment()
    }

    private lateinit var model: NoUserMainViewModel
    private lateinit var binding: FragmentNoUserMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoUserMainBinding.inflate(inflater, container, false)
        model = ViewModelProvider(this)[NoUserMainViewModel::class.java]

        val navController = findNavController()

        if(model.isThereCurrentUser()){
            val action = NoUserMainFragmentDirections.actionNoUserMainFragmentToProfileMainFragment()
            navController.navigate(action)
        }

        with(binding){
            noUserMainLogInButton.setOnClickListener {
                val action = NoUserMainFragmentDirections.actionNoUserMainFragmentToLogInFragment()
                navController.navigate(action)
            }
            noUserMainSingUpButton.setOnClickListener{
                val action = NoUserMainFragmentDirections.actionNoUserMainFragmentToSignUpFragment()
                navController.navigate(action)
            }
            val fab = binding.noUserMainInfoFloatingActionButton
            fab.setOnClickListener {
                val snack = Snackbar.make(noUserMainSnackBarCoordinatorLayout, getString(R.string.noUserMain_floatingButton_text), 7000)
                snack.setTextMaxLines(5)
                    .setTextColor(resources.getColor(R.color.colorOnPrimary))
                    .setBackgroundTint(resources.getColor(R.color.colorPrimary))
                    .setAction("", null).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}