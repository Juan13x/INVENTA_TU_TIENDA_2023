package com.example.inventa_tu_tienda_2023.ui.profile.with_user.company_data

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inventa_tu_tienda_2023.R

class CompanyDataFragment : Fragment() {

    companion object {
        fun newInstance() = CompanyDataFragment()
    }

    private lateinit var viewModel: CompanyDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_company_data, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CompanyDataViewModel::class.java)
        // TODO: Use the ViewModel
    }

}