package org.pi.controller

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import org.pi.model.Login
import org.pi.model.User
import org.pi.service.CookieService
import org.pi.usecase.UserUseCase

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/user")
class UserController(
    private val userUseCase: UserUseCase,
    private val cookieService: CookieService
) {
    @POST
    @Path("/login")
    fun login(
        @RequestBody login: Login,
    ): Response {
        val response = userUseCase.login(login)

        if (response.error != null) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build()
        }

        val cookie = cookieService.generateCookieFromToken(
            token = response.data!!
        )

        return Response.ok(response).cookie(cookie).build()
    }

    @POST
    @Path("/create")
    fun create(
        @RequestBody user: User,
    ): Response {
        val response = userUseCase.create(user)

        if (response.error != null) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build()
        }

        return Response.ok(response).build()
    }

    @GET
    @Path("/me")
    fun create(): Response {
        val response = userUseCase.currentUser()

        if (response.error != null) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build()
        }

        return Response.ok(response).build()
    }
}

