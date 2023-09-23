package org.gusanta.toserba.controller;

import java.util.List;

import org.gusanta.toserba.core.util.CommonStatic;
import org.gusanta.toserba.handler.BarangHandler;
import org.gusanta.toserba.model.body.BarangBody;
import org.gusanta.toserba.model.entity.BarangEntity;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(CommonStatic.V1 + "/barang")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BarangController {

    @Inject
    BarangHandler barangHandler;
    
    @GET
    @Path("/get/{id}")
    public BarangEntity getBarangById(Long id){
        return barangHandler.getBarangById(id);
    }

    @GET
    @Path("/get-all")
    public List<BarangEntity> getAllBarang(){
        return barangHandler.getAllBarang();
    }

    @POST
    @Path("/create")
    @Transactional
    public BarangEntity createBarang(BarangBody barangBody){
        return barangHandler.createBarang(barangBody);
    }

    @PUT
    @Path("/update")
    @Transactional
    public BarangEntity updateBarang(BarangEntity barangEntity){
        return barangHandler.updateBarang(barangEntity);
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deletBarangById(Long id){
        return barangHandler.deleteBarangById(id);
    }
}
