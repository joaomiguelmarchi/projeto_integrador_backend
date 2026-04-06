package org.pi.repository

import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.pi.model.User

@ApplicationScoped
class UserRepository(
    private val userRepositoryJPA: UserRepositoryJPA,
) {
    @Transactional
    fun getUserByEmail(email: String): User {
        val result = userRepositoryJPA.find("email", email).firstResult<UserJPA>()

        return result.toUser()
    }

    @Transactional
    fun insertNewUser(user: User): User {
        val userJpa = UserJPA()

        userJpa.name = user.name!!
        userJpa.email = user.email!!
        userJpa.password = user.password!!

        userRepositoryJPA.persist(userJpa)

        return userJpa.toUser()
    }
}