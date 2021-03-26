package com.example.intellifyhomeassignment.UI

import java.io.Serializable


data class User(
    var name: String? = null,
    var age: String? = null,
    var city: String? = null,
    var gender: String? = null,
    var email: String? = null,
    var password: String? = null
) :Serializable


    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.

