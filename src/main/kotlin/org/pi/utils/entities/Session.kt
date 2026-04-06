package org.pi.utils.entities

import io.quarkus.security.identity.SecurityIdentity
import jakarta.enterprise.context.ApplicationScoped
import org.pi.model.User

@ApplicationScoped
class Session(
    private val identity: SecurityIdentity,
){
    fun getUser(): User = identity.getAttribute("user")
}