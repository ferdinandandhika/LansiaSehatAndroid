package com.example.lansiasehat.ui.kuis

data class Question(
    val id: Int,
    val questionText: String,
    val image: Int,
    val alternatives: ArrayList<String>,
    val correctAnswerIndex: Int,
    val isVideo: Boolean = false,
    val mediaUri: String? = null
)
