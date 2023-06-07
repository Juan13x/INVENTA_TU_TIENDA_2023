package com.example.inventa_tu_tienda_2023.models.Register

data class SignUpData(
    val email: String,
    val password: String,
    val repPassword: String,
    val number: String,
    val companyName: String,
    val userName: String
)
