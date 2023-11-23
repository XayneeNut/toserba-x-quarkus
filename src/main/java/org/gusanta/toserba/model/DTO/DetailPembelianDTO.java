package org.gusanta.toserba.model.DTO;

import java.time.LocalDateTime;

import org.gusanta.toserba.model.entity.DetailPembelianEntity;

public class DetailPembelianDTO {
    public Long detailPembelianId;
    public Long jumlahBarang;
    public Long subtotal;
    public LocalDateTime pembelianDate;
    public Long hargaSatuan;
    public String catatan;
    public String alamatPengiriman;

    public DetailPembelianDTO(DetailPembelianEntity detailPembelianEntity) {
        this.detailPembelianId = detailPembelianEntity.detailPembelianId;
        this.jumlahBarang = detailPembelianEntity.jumlahBarang;
        this.subtotal = detailPembelianEntity.subtotal;
        this.pembelianDate = detailPembelianEntity.pembelianDate;
        this.hargaSatuan = detailPembelianEntity.hargaSatuan;
        this.catatan = detailPembelianEntity.catatan;
        this.alamatPengiriman = detailPembelianEntity.alamatPengiriman;
    }
}
