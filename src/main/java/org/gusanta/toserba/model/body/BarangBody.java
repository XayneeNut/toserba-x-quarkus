package org.gusanta.toserba.model.body;

import org.gusanta.toserba.model.entity.BarangEntity;

public record BarangBody(String namaBarang,
        Long hargaBarang,
        Long stokBarang,
        String kodeBarang,
        Long adminAccountEntity,
        Long hargaJual,
        String unit,
        String deskripsi) { 

    public BarangEntity mapBarangEntity() {
        var barangEntity = new BarangEntity();
        barangEntity.namaBarang = namaBarang;
        barangEntity.hargaBarang = hargaBarang;
        barangEntity.stokBarang = stokBarang;
        barangEntity.kodeBarang = kodeBarang;
        barangEntity.hargaJual = hargaJual;
        barangEntity.unit = unit;
        barangEntity.deskripsi = deskripsi;
        return barangEntity;
    }
}
