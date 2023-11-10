package org.gusanta.toserba.controller;

import java.util.List;

import org.gusanta.toserba.core.util.CommonStatic;
import org.gusanta.toserba.handler.PembelianHandler;
import org.gusanta.toserba.model.body.PembelianBody;
import org.gusanta.toserba.model.entity.PembelianEntity;

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
@Path(CommonStatic.V1 + "/pembelian")
public class PembelianController {

    @Inject
    PembelianHandler pembelianHandler;

    @GET
    @Path("/get-id/{id}")
    public PembelianEntity getPembelianEntityById(Long id) {
        return pembelianHandler.getPembelianById(id);
    }

    @GET
    @Path("/get-all")
    public List<PembelianEntity> getAllPembelianEntity() {
        return pembelianHandler.getAllPembelianEntities();
    }

    @POST
    @Transactional
    @Path("/create")
    public PembelianEntity createPembelianEntity(PembelianBody pembelianBody) {
        return pembelianHandler.createPembelian(pembelianBody);
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    public Response deletePembelianEntity(Long id) {
        return pembelianHandler.deletePembelianById(id);
    }

}
