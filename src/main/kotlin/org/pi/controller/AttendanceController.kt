package org.pi.controller

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import org.pi.model.Attendance
import org.pi.usecase.AttendanceQueueUseCase

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/attendance")
class AttendanceController(
    private val attendanceQueueUseCase: AttendanceQueueUseCase,
) {
    @POST
    @Path("/register")
    fun register(
        @RequestBody attendance: Attendance,
    ): Response {
        val response = attendanceQueueUseCase.register(attendance)

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
        val response = attendanceQueueUseCase.list()

        if (response.error != null) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build()
        }

        return Response.ok(response).build()
    }
}
