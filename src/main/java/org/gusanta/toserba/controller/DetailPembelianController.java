package org.gusanta.toserba.controller;

import java.util.List;

import org.gusanta.toserba.core.util.CommonStatic;
import org.gusanta.toserba.handler.DetailPembelianHandler;
import org.gusanta.toserba.model.body.DetailPembelianBody;
import org.gusanta.toserba.model.entity.DetailPembelianEntity;

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
@Path(CommonStatic.V1 + "/detail-pembelian")
public class DetailPembelianController {

    @Inject
    DetailPembelianHandler detailPembelianHandler;

    @GET
    @Path("/get-id/{id}")
    public DetailPembelianEntity getDetailPembelianById(Long id){
        return detailPembelianHandler.getDetailPembelianById(id);
    }

    @GET
    @Path("/get-all")
    public List<DetailPembelianEntity> getAllDetailPembelian(){
        return detailPembelianHandler.getAllDetailPembelianEntities();
    }

    @POST
    @Transactional
    @Path("/create")
    public DetailPembelianEntity createDetailPembelian(DetailPembelianBody detailPembelianBody){
        return detailPembelianHandler.createDetailPembelian(detailPembelianBody);
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    public Response deleteDetailPembelianById(Long id){
        return detailPembelianHandler.deleteDetailPembelian(id);
    }
    
}
