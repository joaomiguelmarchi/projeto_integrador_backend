package org.pi.controller

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import org.pi.model.Login
import org.pi.model.Procedure
import org.pi.usecase.ProcedureUseCase
import org.pi.usecase.UserUseCase

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/procedure")
class ProcedureController(
    private val procedureUseCase: ProcedureUseCase,
) {
    @POST
    @Path("/register")
    fun login(
        @RequestBody procedure: Procedure,
    ): Response {
        val response = procedureUseCase.insertProcedure(procedure)

        if (response.error != null) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build()
        }

        return Response.ok(response).build()
    }

    @GET
    @Path("/list")
    fun list(): Response {
        val response = procedureUseCase.list()

        if (response.error != null) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build()
        }

        return Response.ok(response).build()
    }

    @GET
    @Path("/details/{id}")
    fun details(
        @PathParam("id") id: Int,
    ): Response {
        val response = procedureUseCase.details(id)

        if (response.error != null) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build()
        }

        return Response.ok(response).build()
    }

    @DELETE
    @Path("/delete")
    fun delete(
        @QueryParam("id") id: Int,
    ): Response {
        val response = procedureUseCase.delete(id)

        if (response.error != null) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build()
        }

        return Response.ok(response).build()
    }

    @POST
    @Path("/edit")
    fun edit(
        @RequestBody procedure: Procedure,
    ): Response {
        val response = procedureUseCase.edit(procedure)

        if (response.error != null) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build()
        }

        return Response.ok(response).build()
    }
}
