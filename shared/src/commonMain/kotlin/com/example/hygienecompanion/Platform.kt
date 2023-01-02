package com.example.hygienecompanion

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform