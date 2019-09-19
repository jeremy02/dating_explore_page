package com.example.datematch.models

data class Users(
    val id: Long = counter++,
    val name: String,
    val city: String,
    val url: String,
    val hobbies: String
) {
    companion object {
        private var counter = 0L
    }
}
