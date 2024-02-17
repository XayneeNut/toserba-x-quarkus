package org.gusanta.toserba.model.body;

import java.io.InputStream;
import java.util.UUID;

import org.gusanta.toserba.model.entity.ImageBarangEntity;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;

public class ImageBody {
    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream file;

    @FormParam("barang")
    public Long barang;

    public ImageBarangEntity mapImageEntity() {
        var imageEntity = new ImageBarangEntity();
        imageEntity.gambarId = generateId();
        
        return imageEntity;
    }

    private Long generateId() {
        UUID uuid = UUID.randomUUID();
        return uuid.getMostSignificantBits();
    }
}
