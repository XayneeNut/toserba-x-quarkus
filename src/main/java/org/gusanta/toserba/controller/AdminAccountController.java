package org.gusanta.toserba.controller;

import java.util.List;

import org.gusanta.toserba.core.util.CommonStatic;
import org.gusanta.toserba.handler.AdminAccountHandler;
import org.gusanta.toserba.model.body.AdminAccountBody;
import org.gusanta.toserba.model.entity.AdminAccountEntity;

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
@Path(CommonStatic.V1 + "/admin-account")
public class AdminAccountController {

    @Inject
    AdminAccountHandler adminAccountHandler;
    
    @GET
    @Path("/get-id/{id}")
    public AdminAccountEntity getAdminAccountEntityById(Long id){
        return adminAccountHandler.getAdminAccountEntityById(id);
    }

    @GET
    @Path("/get/{email}")
    public AdminAccountEntity getAdminAccountEntityByEmail(String email){
        return adminAccountHandler.getAdminAccountEntityByEmail(email);
    }

    @GET
    @Path("/get/{username}")
    public AdminAccountEntity getAdminAccountEntityByUsername(String username){
        return adminAccountHandler.getAdminAccountEntityByUsername(username);
    }

    @GET
    @Path("/get/{password}")
    public AdminAccountEntity getAdminByPassword(String password){
        return adminAccountHandler.getAdminAccountEntityByPassword(password);
    }

    @GET
    @Path("/get/{email}/{password}")
    public AdminAccountEntity getAdminAccountEntityByEmailAndPassword(String email, String password){
        return adminAccountHandler.getAdminAccountEntityByEmailAndPassword(email, password);
    }

    @GET
    @Path("/get-all")
    public List<AdminAccountEntity> getAllAdminAccountEntities(){
        return adminAccountHandler.getAllAdminAccountEntity();
    }

    @POST
    @Path("/create")
    @Transactional
    public AdminAccountEntity createAdminAccountEntity(AdminAccountBody adminAccountBody){
        return adminAccountHandler.createAdminAccountEntity(adminAccountBody);
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteAdminAccountEntityById(Long id){
        return adminAccountHandler.deleteAdminAccountEntityById(id);
    }
}
