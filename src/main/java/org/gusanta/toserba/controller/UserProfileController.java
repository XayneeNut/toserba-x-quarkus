package org.gusanta.toserba.controller;

import java.util.List;

import org.gusanta.toserba.core.util.CommonStatic;
import org.gusanta.toserba.handler.UserProfileHandler;
import org.gusanta.toserba.model.body.UserProfileBody;
import org.gusanta.toserba.model.entity.UserProfileEntity;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path(CommonStatic.V1 + "/user-profile")
public class UserProfileController {

    @Inject
    UserProfileHandler userProfileHandler;

    @GET
    @Path("/get/{id}")
    public UserProfileEntity getUserProfileById(Long id) {
        return userProfileHandler.getUserProfileEntityById(id);
    }

    @GET
    @Path("/get-all")
    public List<UserProfileEntity> getAllUserProfileEntity() {
        return userProfileHandler.getAllUserProfileEntity();
    }

    @POST
    @Path("/create")
    @Transactional
    public UserProfileEntity createUserProfileEntity(UserProfileBody userProfileBody){
        return userProfileHandler.createUserProfile(userProfileBody);
    }

}
