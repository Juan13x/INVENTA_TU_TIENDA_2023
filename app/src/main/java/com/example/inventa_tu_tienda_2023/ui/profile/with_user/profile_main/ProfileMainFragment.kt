package com.example.inventa_tu_tienda_2023.ui.profile.with_user.profile_main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.inventa_tu_tienda_2023.R
import com.example.inventa_tu_tienda_2023.databinding.FragmentProfileMainBinding
import com.example.inventa_tu_tienda_2023.ui.profile.no_user.no_user_main.NoUserMainFragmentDirections
import com.example.inventa_tu_tienda_2023.ui.shared.SharedViewModel

class ProfileMainFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileMainFragment()
    }

    private lateinit var model: ProfileMainViewModel
    private lateinit var binding: FragmentProfileMainBinding
    private lateinit var sharedModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentProfileMainBinding.inflate(layoutInflater)
        model = ViewModelProvider(this)[ProfileMainViewModel::class.java]
        sharedModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        with(binding){
            model.getUser(sharedModel)

            profileMainLogoutButton.setOnClickListener {
                model.logout(sharedModel)
                val action = ProfileMainFragmentDirections.actionProfileMainFragmentToNoUserMainFragment()
                findNavController().navigate(action)
            }

            model.userToPrintLiveData.observe(viewLifecycleOwner){
                    userTextToPrint ->
                profileMainWelcomeTextView.text = getString(R.string.profileMain_welcome_text, userTextToPrint)
            }
        }

        return binding.root
    }

}