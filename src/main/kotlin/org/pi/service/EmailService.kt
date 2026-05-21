package org.pi.service

import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.pi.external.ResendClient
import org.pi.utils.entities.Email
import org.pi.utils.entities.ResendEmailRequest

@ApplicationScoped
class EmailService(
    @RestClient
    private val client: ResendClient,
) {
    @ConfigProperty(name = "resend.api.from")
    private lateinit var from: String

    @ConfigProperty(name = "resend.api.key")
    private lateinit var apiKey: String

    fun publish(message: Email) {
        val requestObj = ResendEmailRequest(
            from = from,
            to = message.to,
            subject = message.subject,
            html = message.content,
        )

        client.sendEmail(requestObj, "Bearer $apiKey")
    }
}