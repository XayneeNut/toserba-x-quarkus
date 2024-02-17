package org.gusanta.toserba.controller;

import java.util.List;

import org.gusanta.toserba.core.util.CommonStatic;
import org.gusanta.toserba.handler.UserAddressHandler;
import org.gusanta.toserba.model.body.UserAddressBody;
import org.gusanta.toserba.model.entity.UserAddressEntity;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path(CommonStatic.V1 + "/user-address")
public class UserAddressController {
    
    @Inject
    UserAddressHandler userAddressHandler;

    @GET
    @Path("/get-all")
    public List<UserAddressEntity> getAllUserAddress(){
        return userAddressHandler.getAllUserAddressEntities();
    }

    @GET
    @Path("/get/{id}")
    public UserAddressEntity getUserAddressById(@PathParam("id") Long id){
        return userAddressHandler.getUserAddressEntityById(id);
    }

    @POST
    @Transactional
    @Path("/create")
    public UserAddressEntity createUserAddress(UserAddressBody body){
        return userAddressHandler.createUserAddress(body);
    }
}
