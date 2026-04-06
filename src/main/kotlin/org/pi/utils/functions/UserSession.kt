package org.pi.utils.functions

import io.quarkus.security.identity.SecurityIdentity
import jakarta.enterprise.context.ApplicationScoped
import org.pi.model.User

@ApplicationScoped
class UserSession(
    private val identity: SecurityIdentity,
) {
    fun getUser(): User = identity.getAttribute("user")
}