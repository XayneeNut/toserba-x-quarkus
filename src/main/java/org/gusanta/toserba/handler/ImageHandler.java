package org.gusanta.toserba.handler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.gusanta.toserba.exception.response.MessageResponse;
import org.gusanta.toserba.model.body.ImageBody;
import org.gusanta.toserba.model.entity.BarangEntity;
import org.gusanta.toserba.model.entity.ImageBarangEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ImageHandler {

    @Inject
    EntityManager entityManager;

    public ImageBarangEntity getImageBarangById(Long id) {
        return ImageBarangEntity.findImageEntityById(id).orElseThrow(() -> MessageResponse.idNotFoundException(id));
    }

    private BarangEntity fetchBarangEntity(Long id) {
        return BarangEntity.findBarangEntityById(id)
                .orElseThrow(() -> MessageResponse.fetchMessageException(id, "barangId"));
    }

    private ImageBarangEntity checkingWithCreate(ImageBody imageBody, BarangEntity barangEntity) {
        try {
            InputStream originalImageStream = imageBody.file;
            BufferedImage originalImage = ImageIO.read(originalImageStream);

            int scaledWidth = 200;

            int scaledHeight = (int) (scaledWidth * ((double) originalImage.getHeight() / originalImage.getWidth()));

            BufferedImage resizedImage = new BufferedImage(scaledWidth, scaledHeight, originalImage.getType());
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
            var image = imageBody.mapImageEntity();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", byteArrayOutputStream);
            byte[] imagesBytes = byteArrayOutputStream.toByteArray();
            String imageString = Base64.getEncoder().encodeToString(imagesBytes);
            image.setGambar(imageString);
            image.barangEntity = barangEntity;
            image.persist();
            return image;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Response createBarang(ImageBody imageBody) {
        Objects.requireNonNull(imageBody);
        var barangEntity = fetchBarangEntity(imageBody.barang);
        checkingWithCreate(imageBody, barangEntity);

        return Response.ok("image berhasil dipost").build();
    }

    @Transactional
    public Response deleteImageById(Long id) {
        var ada = entityManager.find(ImageBarangEntity.class, id);

        if (ada != null) {
            try {
                entityManager.remove(ada); // Menghapus entitas dari database
                entityManager.flush(); // Komit transaksi
                entityManager.clear(); // Bersihkan konteks
                String message = "Image with ID " + id + " has been successfully deleted.";
                return Response.status(Response.Status.OK).entity(message).build();
            } catch (Exception e) {
                e.printStackTrace();
                String errorMessage = "Failed to delete image with ID " + id + ".";
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
            }
        } else {
            String notFoundMessage = "Image with ID " + id + " not found.";
            return Response.status(Response.Status.NOT_FOUND).entity(notFoundMessage).build();
        }
    }

}
