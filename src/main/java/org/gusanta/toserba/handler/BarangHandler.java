package org.gusanta.toserba.handler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.gusanta.toserba.exception.response.MessageResponse;
import org.gusanta.toserba.model.body.BarangBody;
import org.gusanta.toserba.model.entity.AdminAccountEntity;
import org.gusanta.toserba.model.entity.BarangEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class BarangHandler {
    public BarangEntity getBarangById(Long id) {
        return BarangEntity.findBarangEntityById(id).orElseThrow(() -> MessageResponse.idNotFoundException(id));
    }

    public List<BarangEntity> getAllBarang() {
        return BarangEntity.findAllBarangEntity().stream().collect(Collectors.toList());
    }

    public AdminAccountEntity fetchAdminAccountEntity(Long id) {
        return AdminAccountEntity.findAdminAccountById(id)
                .orElseThrow(() -> MessageResponse.fetchMessageException(id, "adminAccountEntity"));
    }

    private BarangEntity checkingWithCreate(BarangBody barangBody, AdminAccountEntity adminAccountEntity) {

        var barang = barangBody.mapBarangEntity();
        barang.adminAccountEntity = adminAccountEntity;
        barang.persist();
        return barang;

    }

    public BarangEntity createBarang(BarangBody barangBody) {
        Objects.requireNonNull(barangBody);
        var adminAccountEntity = fetchAdminAccountEntity(barangBody.adminAccountEntity());
        return checkingWithCreate(barangBody, adminAccountEntity);
    }

    public BarangEntity updateBarang(BarangEntity barangEntity) {
        var validatingId = getBarangById(barangEntity.idBarang);
        barangEntity.updateBarangEntity(validatingId);
        return validatingId;
    }

    public Response deleteBarangById(Long id) {
        if (getBarangById(id) != null) {
            BarangEntity.deleteById(id);
            return MessageResponse.deleteSucces(id);
        } else {
            return MessageResponse.idNotFound(id);
        }
    }
}
