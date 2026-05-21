package org.pi.external

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.HeaderParam
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.pi.utils.entities.ResendEmailRequest

@Path("/emails")
@RegisterRestClient(configKey = "resend-api")
interface ResendClient {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun sendEmail(
        request: ResendEmailRequest,
        @HeaderParam("Authorization") authorization: String,
    )
}