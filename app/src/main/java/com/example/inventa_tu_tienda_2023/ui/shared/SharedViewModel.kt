package com.example.inventa_tu_tienda_2023.ui.shared

import androidx.lifecycle.ViewModel
import com.example.inventa_tu_tienda_2023.models.User

class SharedViewModel: ViewModel() {
    var user = User(",", "", "", "")
}