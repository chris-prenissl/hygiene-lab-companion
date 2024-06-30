package com.christophprenissl.hygienecompanion.model.entity

data class Address(
    val id: String = "",
    val name: String,
    val zip: String,
    val city: String,
    val contactName: String = "",
    val street: String,
    val phone: String = "",
    val fax: String = "",
    val eMail: String = "",
)
