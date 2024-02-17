package org.gusanta.toserba.model.body;

import org.gusanta.toserba.model.entity.PembelianEntity;

public record PembelianBody(
        Long pembelianId,
        Long barangEntity,
        Long userAccountEntity,
        Long detailPembelianEntity) {
    public PembelianEntity mapPembelianEntity() {
        var pembelianEntity = new PembelianEntity();
        return pembelianEntity;
    }
}
