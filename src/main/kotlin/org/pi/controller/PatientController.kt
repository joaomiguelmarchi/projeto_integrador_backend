package org.pi.controller

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import org.pi.model.Patient
import org.pi.usecase.PatientUseCase

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/patient")
class PatientController(
    private val patientUseCase: PatientUseCase,
) {
    @POST
    @Path("/register")
    fun login(
        @RequestBody patient: Patient,
    ): Response {
        val response = patientUseCase.insertPatient(patient)

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
        val response = patientUseCase.list()

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
        val response = patientUseCase.details(id)

        if (response.error != null) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build()
        }

        return Response.ok(response).build()
    }

    @DELETE
    @Path("/delete/{id}")
    fun delete(
        @PathParam("id") id: Int,
    ): Response {
        val response = patientUseCase.delete(id)

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
        @RequestBody patient: Patient,
    ): Response {
        val response = patientUseCase.edit(patient)

        if (response.error != null) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build()
        }

        return Response.ok(response).build()
    }
}