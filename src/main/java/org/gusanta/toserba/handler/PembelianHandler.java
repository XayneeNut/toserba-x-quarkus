package org.gusanta.toserba.handler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.gusanta.toserba.exception.response.MessageResponse;
import org.gusanta.toserba.model.DTO.PembelianDTO;
import org.gusanta.toserba.model.body.PembelianBody;
import org.gusanta.toserba.model.entity.AdminAccountEntity;
import org.gusanta.toserba.model.entity.DetailPembelianEntity;
import org.gusanta.toserba.model.entity.PembelianEntity;
import org.gusanta.toserba.model.entity.UserAccountEntity;
import org.gusanta.toserba.model.entity.UserProfileEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class PembelianHandler {

    public PembelianEntity getPembelianById(Long id) {
        return PembelianEntity.findPembelianById(id).orElseThrow(() -> MessageResponse.idNotFoundException(id));
    }

    public List<PembelianEntity> getAllPembelianEntities() {
        return PembelianEntity.findAllPembelianEntities().stream().collect(Collectors.toList());
    }

    private AdminAccountEntity fetchAdminAccountEntity(Long id) {
        return AdminAccountEntity.findAdminAccountById(id)
                .orElseThrow(() -> MessageResponse.fetchMessageException(id, "Not Found"));
    }

    private UserAccountEntity fetchUserAccountEntity(Long id) {
        return UserAccountEntity.findUserAccountById(id)
                .orElseThrow(() -> MessageResponse.fetchMessageException(id, "Not Found"));
    }

    private DetailPembelianEntity fetchDetailPembelianEntity(Long id) {
        return DetailPembelianEntity.findDetailPembelianById(id)
                .orElseThrow(() -> MessageResponse.fetchMessageException(id, "Not Found"));
    }

    private UserProfileEntity fetchUserProfileEntity(Long id) {
        return UserProfileEntity.findUserProfileById(id)
                .orElseThrow(() -> MessageResponse.fetchMessageException(id, "Not Found"));
    }

    public List<PembelianDTO> getAll() {
        List<PembelianEntity> pembelianEntities = PembelianEntity.findAllPembelianEntities();
        return pembelianEntities.stream().map(PembelianDTO::new).collect(Collectors.toList());
    }

    private PembelianEntity checkingWithCreate(PembelianBody pembelianBody, AdminAccountEntity adminAccountEntity,
            UserAccountEntity userAccountEntity, DetailPembelianEntity detailPembelianEntity,
            UserProfileEntity userProfileEntity) {
        var pembelian = pembelianBody.mapPembelianEntity();
        pembelian.adminAccountEntity = adminAccountEntity;
        pembelian.userAccountEntity = userAccountEntity;
        pembelian.detailPembelianEntity = detailPembelianEntity;
        pembelian.userProfileEntity = userProfileEntity;
        pembelian.persist();
        return pembelian;
    }

    public PembelianEntity createPembelian(PembelianBody pembelianBody) {
        Objects.requireNonNull(pembelianBody);

        var adminAccountEntity = fetchAdminAccountEntity(pembelianBody.adminAccountEntity());
        var userAccountEntity = fetchUserAccountEntity(pembelianBody.userAccountEntity());
        var detailPembelianEntity = fetchDetailPembelianEntity(pembelianBody.detailPembelianEntity());
        var userProfileEntity = fetchUserProfileEntity(pembelianBody.userAccountEntity());

        if (userProfileEntity != null
                && userProfileEntity.userAccountEntity.userAccountId.equals(userAccountEntity.userAccountId)) {
            return checkingWithCreate(pembelianBody, adminAccountEntity, userAccountEntity, detailPembelianEntity,
                    userProfileEntity);
        } else {
            throw new IllegalArgumentException("UserAccountID mismatch in UserProfileEntity");
        }
    }

    public Response deletePembelianById(Long id) {
        if (getPembelianById(id) != null) {
            PembelianEntity.deleteById(id);
            return MessageResponse.deleteSucces(id);
        }
        return MessageResponse.idNotFound(id);
    }
}
