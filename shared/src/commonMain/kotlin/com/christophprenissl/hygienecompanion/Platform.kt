package com.christophprenissl.hygienecompanion

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform