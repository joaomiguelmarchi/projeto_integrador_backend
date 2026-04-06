package org.pi.model

class User(
    var id: Int? = 0,
    var name: String? = "",
    var email: String? = "",
    var password: String? = "",
)

class Login(
    var email: String? = "",
    var password: String? = ""
)

class Token(
    var token: String? = "",
    var expiration: Long? = 0
)