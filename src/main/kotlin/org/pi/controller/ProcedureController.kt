package org.pi.controller

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import org.pi.model.Login
import org.pi.model.Procedure
import org.pi.usecase.UserUseCase

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/procedure")
class ProcedureController(
    private val userUseCase: UserUseCase,
) {
    @POST
    @Path("/register")
    fun login(
        @RequestBody procedure: Procedure,
    ): Response {
        val response = userUseCase.login(login)

        if (response.error != null) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build()
        }

        return Response.ok(response).cookie(cookie).build()
    }
}
