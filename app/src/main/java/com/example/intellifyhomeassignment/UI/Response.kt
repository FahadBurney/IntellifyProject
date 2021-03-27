package com.example.intellifyhomeassignment.UI

import java.io.Serializable
import java.lang.Exception

data class Response(
    var users: List<User>? = null,
    var exception: Exception? = null
):Serializable
