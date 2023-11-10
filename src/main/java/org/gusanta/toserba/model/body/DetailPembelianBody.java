package org.gusanta.toserba.model.body;

import java.time.LocalDateTime;

import org.gusanta.toserba.core.util.jackson.TimeDeserialize;
import org.gusanta.toserba.model.entity.DetailPembelianEntity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record DetailPembelianBody(
        Long detailPembelianId,
        Long jumlahBarang,
        Long subtotal,
        @JsonDeserialize(converter = TimeDeserialize.class) 
        LocalDateTime pembelianDate,
        Long hargaSatuan,
        String catatan,
        String alamatPengiriman) {

    public DetailPembelianEntity mapDetailPembelianEntity() {
        var detailPembelianEntity = new DetailPembelianEntity();

        detailPembelianEntity.jumlahBarang = jumlahBarang;
        detailPembelianEntity.subtotal = subtotal;
        detailPembelianEntity.hargaSatuan = hargaSatuan;
        detailPembelianEntity.catatan = catatan;
        detailPembelianEntity.alamatPengiriman = alamatPengiriman;
        return detailPembelianEntity;
    }

}
