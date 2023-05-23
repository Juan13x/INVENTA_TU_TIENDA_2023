package com.example.inventa_tu_tienda_2023.ui.products

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.inventa_tu_tienda_2023.R
import com.example.inventa_tu_tienda_2023.databinding.FragmentProductsBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class ProductsFragment : Fragment() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var _binding: FragmentProductsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)

        binding.appBarProducts.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val navView: NavigationView = binding.navView
        val navController = findNavController(binding.appBarProducts.root.findViewById(R.id.nav_host_fragment_content_products))
        navView.setupWithNavController(navController)

        (activity as AppCompatActivity).setSupportActionBar(binding.appBarProducts.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_products, R.id.navigation_categories, R.id.navigation_stores, R.id.navigation_profile
            ), drawerLayout
        )
        setupActionBarWithNavController(activity as AppCompatActivity, navController, appBarConfiguration)
        return binding.root
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//        setSupportActionBar(binding.appBarProducts.toolbar)

//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_products, R.id.navigation_categories, R.id.navigation_stores, R.id.navigation_profile
//            ), drawerLayout
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.fragment_products_drawer, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}