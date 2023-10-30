package org.gusanta.toserba.model.body;

import org.gusanta.toserba.model.entity.BarangEntity;

public record BarangBody(
    Long idBarang,
    String namaBarang,
    Long hargaBarang,
    Long stokBarang,
    String kodeBarang,
    String imageBarang
) {
    public BarangEntity mapBarangEntity(){
        var barangEntity = new BarangEntity();
        barangEntity.namaBarang = namaBarang;
        barangEntity.hargaBarang = hargaBarang;
        barangEntity.stokBarang = stokBarang;
        barangEntity.kodeBarang = kodeBarang;
        barangEntity.imageBarang = imageBarang;
        return barangEntity;
    }
}
