package org.gusanta.toserba.controller;

import org.gusanta.toserba.core.util.CommonStatic;
import org.gusanta.toserba.handler.ImageHandler;
import org.gusanta.toserba.model.body.ImageBody;
import org.gusanta.toserba.model.entity.ImageBarangEntity;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(CommonStatic.V1 + "/image")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImageController {

    @Inject
    ImageHandler imageHandler;

    @GET
    @Path("/get/{id}")
    public ImageBarangEntity getImageBarangById(@PathParam("id") Long id) {
        return imageHandler.getImageBarangById(id);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Response postImageEntity(@MultipartForm ImageBody imageBody) {
        return imageHandler.createBarang(imageBody);
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    public Response deleteImageById(@PathParam("id") Long id) {
        return imageHandler.deleteImageById(id);
    }

}
