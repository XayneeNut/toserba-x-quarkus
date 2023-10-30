package org.gusanta.toserba.controller;

import org.gusanta.toserba.core.util.JwtUtil;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/jwt")
@ApplicationScoped
public class JwtTokenController {
    @Inject
    JwtUtil jwtUtil;

    @GET
    @Path("/user-jwt/{email}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getJwt(@PathParam("email") String email) {
        String jwt = JwtUtil.generateToken(email);

        return Response.ok(jwt).build();
    }

    @GET
    @Path("/user-jwt")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getJwt() {
        String jwt = JwtUtil.generateToken();

        return Response.ok(jwt).build();
    }

    @GET
    @Path("/admin-jwt")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAdminJwt() {
        String jwt = JwtUtil.generateAdminToken();

        return Response.ok(jwt).build();
    }
}
