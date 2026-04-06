package org.pi.usecase

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.pi.model.Login
import org.pi.model.User
import org.pi.repository.UserRepository
import org.pi.model.Token
import org.pi.service.TokenService
import org.pi.utils.entities.DefaultResponse
import org.pi.utils.entities.Session
import org.pi.utils.functions.AUTH_EMAIL_IS_EMPTY
import org.pi.utils.functions.AUTH_NAME_IS_EMPTY
import org.pi.utils.functions.AUTH_PASSWORD_IS_EMPTY
import org.pi.utils.functions.INVALID_CREDENTIALS
import org.pi.utils.functions.UNEXPECTED
import org.springframework.security.crypto.password.PasswordEncoder

@ApplicationScoped
class UserUseCase(
    private val userRepository: UserRepository,
    private val session: Session
) {
    @Inject
    private lateinit var encoder: PasswordEncoder

    @Inject
    private lateinit var tokenService: TokenService

    fun create(user: User): DefaultResponse<User> {
        try {
            if (user.name == "") {
                return DefaultResponse(
                    error = AUTH_NAME_IS_EMPTY
                )
            }

            if (user.email == "") {
                return DefaultResponse(
                    error = AUTH_EMAIL_IS_EMPTY
                )
            }

            if (user.password == "") {
                return DefaultResponse(
                    error = AUTH_PASSWORD_IS_EMPTY
                )
            }

            user.password = encoder.encode(user.password)

            val created = userRepository.insertNewUser(user)

            created.password = ""

            return DefaultResponse(
                data = created
            )
        } catch (e: Exception) {
            return DefaultResponse(
                error = UNEXPECTED
            )
        }
    }

    fun login(login: Login): DefaultResponse<Token> {
        try {
            if (login.email == null || login.email!! == "") {
                return DefaultResponse(
                    error = AUTH_EMAIL_IS_EMPTY
                )
            }

            val user = userRepository.getUserByEmail(login.email!!)

            val matches = encoder.matches(login.password!!, user.password)

            if (!matches) {
                return DefaultResponse(
                    error = INVALID_CREDENTIALS
                )
            }

            val token = tokenService.generateToken(user.email!!)

            return DefaultResponse(
                data = token
            )
        } catch (e: Exception) {
            return DefaultResponse(
                error = UNEXPECTED
            )
        }
    }

    fun currentUser(): DefaultResponse<User> {
        try {
            val user = session.getUser()

            return DefaultResponse(
                data = user
            )
        } catch (e: Exception) {
            return DefaultResponse(
                error = UNEXPECTED
            )
        }
    }
}