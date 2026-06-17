package org.pi.usecase

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.pi.model.Login
import org.pi.model.PasswordRecovery
import org.pi.model.User
import org.pi.repository.UserRepository
import org.pi.model.Token
import org.pi.service.TokenService
import org.pi.utils.entities.DefaultResponse
import org.pi.utils.entities.Email
import org.pi.utils.entities.Session
import org.pi.utils.functions.AUTH_EMAIL_IS_EMPTY
import org.pi.utils.functions.AUTH_NAME_IS_EMPTY
import org.pi.utils.functions.AUTH_PASSWORD_IS_EMPTY
import org.pi.utils.functions.INVALID_CREDENTIALS
import org.pi.utils.functions.UNEXPECTED
import org.pi.workers.EmailQueue
import org.springframework.security.crypto.password.PasswordEncoder

@ApplicationScoped
class UserUseCase(
    private val userRepository: UserRepository,
    private val session: Session,
    private val mailQueue: EmailQueue
) {
    @Inject
    private lateinit var encoder: PasswordEncoder

    @Inject
    private lateinit var tokenService: TokenService

    @ConfigProperty(name = "app.password-recovery.url")
    private lateinit var passwordRecoveryUrl: String

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

            val userResponse = User(
                id = created.id,
                name = created.name,
                email = created.email,
                password = null
            )

            return DefaultResponse(
                data = userResponse
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

            if (login.password == null || login.password!! == "") {
                return DefaultResponse(error = AUTH_PASSWORD_IS_EMPTY)
            }

            val user = userRepository.getUserByEmail(login.email!!)

            val matches = encoder.matches(login.password!!, user.password!!)

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

    fun recoverPassword(passwordRecovery: PasswordRecovery): DefaultResponse<Boolean> {
        try {
            if (passwordRecovery.email == null || passwordRecovery.email!! == "") {
                return DefaultResponse(
                    error = AUTH_EMAIL_IS_EMPTY
                )
            }

            val user = userRepository.getUserByEmail(passwordRecovery.email!!)

            mailQueue.push(Email(
                subject = "Recuperacao de senha",
                content = """
                    <p>Ola, ${user.name}.</p>
                    <p>Recebemos uma solicitacao para alterar sua senha.</p>
                    <p><a href="$passwordRecoveryUrl">clique aqui para alterar senha</a></p>
                """.trimIndent(),
                to = listOf(user.email!!),
                withHtml = true,
            ))

            return DefaultResponse(
                data = true
            )
        } catch (e: Exception) {
            println(e.message)
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
