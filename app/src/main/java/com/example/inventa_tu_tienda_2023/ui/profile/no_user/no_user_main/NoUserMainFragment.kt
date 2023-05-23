package com.example.inventa_tu_tienda_2023.ui.profile.no_user.no_user_main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inventa_tu_tienda_2023.R

class NoUserMainFragment : Fragment() {

    companion object {
        fun newInstance() = NoUserMainFragment()
    }

    private lateinit var viewModel: NoUserMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_no_user_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NoUserMainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}