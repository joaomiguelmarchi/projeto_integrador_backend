package org.pi.service

import io.smallrye.jwt.build.Jwt
import jakarta.inject.Singleton
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.time.Duration
import java.time.Instant
import org.pi.model.Token

@Singleton
class TokenService {
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    lateinit var issuer: String

    fun generateToken(email: String): Token {
        val expiration = Duration.ofDays(7)
        val now = Instant.now()

        val instant = now.plus(expiration)

        val token = Jwt.issuer(issuer)
            .subject(email)
            .expiresAt(instant)
            .sign()

        val timer = expiration.toMillis()

        return Token(
            token = token,
            expiration = timer
        )
    }
}