package org.gusanta.toserba.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.CreationTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "detail_pembelian")
public class DetailPembelianEntity extends PanacheEntityBase{
    
    @Id
    @GeneratedValue(generator = "gusanta_id_gen")
    @Column(name = "detail_pembelian_id")
    public Long detailPembelianId;

    @Column(name = "jumlah_barang")
    public Long jumlahBarang;

    @Column(name = "subtotal")
    public Long subtotal;

    @CreationTimestamp
    @Column(name = "pembelian_date")
    public LocalDateTime pembelianDate;

    @Column(name = "harga_satuan")
    public Long hargaSatuan;

    @Column(name = "catatan")
    public String catatan;

    @Column(name = "alamat_pengiriman")
    public String alamatPengiriman;

    public static Optional<DetailPembelianEntity> findDetailPembelianById(Long id){
        return find("id = ? 1", id).firstResultOptional();
    }

    public static List<DetailPembelianEntity> findAllDetailPembelianEntities(){
        return DetailPembelianEntity.listAll();
    }
}
