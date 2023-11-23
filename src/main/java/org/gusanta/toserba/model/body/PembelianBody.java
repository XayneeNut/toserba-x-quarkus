package org.gusanta.toserba.model.body;

import org.gusanta.toserba.model.entity.PembelianEntity;

public record PembelianBody(
        Long pembelianId,
        Long adminAccountEntity,
        Long userAccountEntity,
        Long userProfileEnity,
        Long detailPembelianEntity) {
    public PembelianEntity mapPembelianEntity() {
        var pembelianEntity = new PembelianEntity();
        return pembelianEntity;
    }
}
