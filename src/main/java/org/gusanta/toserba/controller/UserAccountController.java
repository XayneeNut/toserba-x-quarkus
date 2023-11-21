package org.gusanta.toserba.controller;

import java.util.List;

import org.gusanta.toserba.core.util.CommonStatic;
import org.gusanta.toserba.handler.UserAccountHandler;
import org.gusanta.toserba.model.DTO.UserAccountDTO;
import org.gusanta.toserba.model.body.UserAccountBody;
import org.gusanta.toserba.model.entity.UserAccountEntity;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path(CommonStatic.V1 + "/user-account")
public class UserAccountController {

    @Inject
    UserAccountHandler userAccountHandler;

    @GET
    @Path("/get-id/{id}")
    public UserAccountEntity getUserAccountEntityById(Long id) {
        return userAccountHandler.getUserAccountEntityById(id);
    }

    @GET
    @Path("/get-all")
    public List<UserAccountEntity> getAllUserAccountEntities(){
        return userAccountHandler.getAllUserAccountEntities();
    }

    @GET
    @Path("/get-all-by-profile")
    public List<UserAccountDTO> getAllByProfile(){
        return userAccountHandler.getAllUserAccountEntitiesAsDTO();
    }

    @GET
    @Path("/get-email/{email}")
    public UserAccountEntity getUserAccountEntityByEmail(String email) {
        return userAccountHandler.getUserAccountEntityByEmail(email);
    }

    @GET
    @Path("/get-password/{password}")
    public UserAccountEntity getUserAccountEntityByPassword(String password) {
        return userAccountHandler.getUserAccountEntityByPassword(password);
    }

    @GET
    @Path("/get/{email}/{password}")
    public UserAccountEntity getUserAccountEntityByEmailAndPassword(String email, String password) {
        return userAccountHandler.getUserAccountEntityByEmailAndPassword(email, password);
    }

    @POST
    @Transactional
    @Path("/create")
    public UserAccountEntity createUserAccountEntity(UserAccountBody userAccountBody) {
        return userAccountHandler.createUserAccountEntity(userAccountBody);
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    public Response deleteUserAccountEntityById(Long id) {
        return userAccountHandler.deleteUserAccountEntityById(id);
    }
}
