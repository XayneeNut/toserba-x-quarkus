package org.gusanta.toserba.handler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.gusanta.toserba.exception.response.MessageResponse;
import org.gusanta.toserba.model.body.DetailPembelianBody;
import org.gusanta.toserba.model.entity.DetailPembelianEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class DetailPembelianHandler {

    public DetailPembelianEntity getDetailPembelianById(Long id) {
        return DetailPembelianEntity.findDetailPembelianById(id)
                .orElseThrow(() -> MessageResponse.idNotFoundException(id));
    }

    public List<DetailPembelianEntity> getAllDetailPembelianEntities() {
        return DetailPembelianEntity.findAllDetailPembelianEntities().stream().collect(Collectors.toList());
    }



    public DetailPembelianEntity createDetailPembelian(DetailPembelianBody detailPembelianBody) {
        Objects.requireNonNull(detailPembelianBody);
        var detailPembelian = detailPembelianBody.mapDetailPembelianEntity();
        detailPembelian.persist();
        return detailPembelian;
    }

    public Response deleteDetailPembelian(Long id) {
        if (getDetailPembelianById(id) != null) {
            DetailPembelianEntity.deleteById(id);
            return MessageResponse.deleteSucces(id);
        } else {
            return MessageResponse.idNotFound(id);
        }
    }

}
