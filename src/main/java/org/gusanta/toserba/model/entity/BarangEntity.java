package org.gusanta.toserba.model.entity;

import java.util.List;
import java.util.Optional;

import org.gusanta.toserba.core.util.ManipulateUtil;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "barang")
public class BarangEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "gusanta_id_gen")
    @Column(name = "id_barang")
    public Long idBarang;

    @Column(name = "nama_barang")
    public String namaBarang;

    @Column(name = "harga_barang")
    public Long hargaBarang;

    @Column(name = "stok_barang")
    public Long stokBarang;

    @Column(name = "kode_barang")
    public String kodeBarang;

    @Column(name = "image_barang")
    public String imageBarang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    public AdminAccountEntity adminAccountEntity;

    @Column(name = "harga_jual")
    public Long hargaJual;

    @Column(name = "unit")
    public String unit;

    public static Optional<BarangEntity> findBarangEntityById(Long id) {
        return find("idBarang = ? 1", id).firstResultOptional();
    }

    public static List<BarangEntity> findAllBarangEntity() {
        return BarangEntity.listAll();
    }

    public static List<BarangEntity> findBarangEntityByAccountId(Long accountId) {
        EntityManager em = getEntityManager();
        return em
                .createQuery("SELECT b FROM BarangEntity b WHERE b.adminAccountEntity.accountId = :accountId",
                        BarangEntity.class)
                .setParameter("accountId", accountId)
                .getResultList();
    }

    public BarangEntity updateBarangEntity(BarangEntity barangEntity) {
        barangEntity.namaBarang = ManipulateUtil.changeItOrNot(namaBarang, barangEntity.namaBarang);
        barangEntity.hargaBarang = ManipulateUtil.changeItOrNot(hargaBarang, barangEntity.hargaBarang);
        barangEntity.stokBarang = ManipulateUtil.changeItOrNot(stokBarang, barangEntity.stokBarang);
        barangEntity.imageBarang = ManipulateUtil.changeItOrNot(imageBarang, barangEntity.imageBarang);
        barangEntity.hargaJual = ManipulateUtil.changeItOrNot(hargaJual, barangEntity.hargaJual);
        barangEntity.unit = ManipulateUtil.changeItOrNot(unit,barangEntity.unit);
        return barangEntity;
    }

    public Long getIdBarang() {
        return this.idBarang;
    }

    public String getNamaBarang() {
        return this.namaBarang;
    }

    public Long getHargaBarang() {
        return this.hargaBarang;
    }

    public Long getStokBarang() {
        return this.stokBarang;
    }

    public String getKodeBarang() {
        return this.kodeBarang;
    }

    public String getImageBarang() {
        return this.imageBarang;
    }
}
