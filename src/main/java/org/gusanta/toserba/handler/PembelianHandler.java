package org.gusanta.toserba.handler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.gusanta.toserba.exception.response.MessageResponse;
import org.gusanta.toserba.model.body.PembelianBody;
import org.gusanta.toserba.model.entity.BarangEntity;
import org.gusanta.toserba.model.entity.DetailPembelianEntity;
import org.gusanta.toserba.model.entity.PembelianEntity;
import org.gusanta.toserba.model.entity.UserAccountEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class PembelianHandler {

    String message = "Not Found or Format Not Valid";

    public PembelianEntity getPembelianById(Long id) {
        return PembelianEntity.findPembelianById(id).orElseThrow(() -> MessageResponse.idNotFoundException(id));
    }

    public List<PembelianEntity> getAllPembelianEntities() {
        return PembelianEntity.findAllPembelianEntities().stream().collect(Collectors.toList());
    }

    private UserAccountEntity fetchUserAccountEntity(Long id) {
        return UserAccountEntity.findUserAccountById(id)
                .orElseThrow(() -> MessageResponse.fetchMessageException(id, message));
    }

    private BarangEntity fetchBarangEntity(Long id) {
        return BarangEntity.findBarangEntityById(id)
                .orElseThrow(() -> MessageResponse.fetchMessageException(id, message));
    }

    private DetailPembelianEntity fetchDetailPembelianEntity(Long id) {
        return DetailPembelianEntity.findDetailPembelianById(id)
                .orElseThrow(() -> MessageResponse.fetchMessageException(id, message));
    }

    private PembelianEntity checkingWithCreate(PembelianBody pembelianBody, BarangEntity barangEntity,
            UserAccountEntity userAccountEntity, DetailPembelianEntity detailPembelianEntity) {
        var pembelian = pembelianBody.mapPembelianEntity();
        pembelian.userAccountEntity = userAccountEntity;
        pembelian.barangEntity = barangEntity;
        pembelian.detailPembelianEntity = detailPembelianEntity;
        pembelian.persist();
        return pembelian;
    }

    public PembelianEntity createPembelian(PembelianBody pembelianBody) {
        Objects.requireNonNull(pembelianBody);

        var userAccountEntity = fetchUserAccountEntity(pembelianBody.userAccountEntity());
        var barangEntity = fetchBarangEntity(pembelianBody.barangEntity());
        var detailPembelianEntity = fetchDetailPembelianEntity(pembelianBody.detailPembelianEntity());

        return checkingWithCreate(pembelianBody, barangEntity, userAccountEntity, detailPembelianEntity);
    }

    public Response deletePembelianById(Long id) {
        if (getPembelianById(id) != null) {
            PembelianEntity.deleteById(id);
            return MessageResponse.deleteSucces(id);
        }
        return MessageResponse.idNotFound(id);
    }
}
