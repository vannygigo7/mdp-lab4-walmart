package com.sangvaleap.lab4_walmart

import java.io.Serializable

data class User(
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String
): Serializable