package org.gusanta.toserba.model.entity;

import java.util.List;
import java.util.Optional;

import org.gusanta.toserba.core.util.ManipulateUtil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Setter
@Getter
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_account_id")
    public AdminAccountEntity adminAccountEntity;

    @Column(name = "harga_jual")
    public Long hargaJual;

    @Column(name = "unit")
    public String unit;

    @Column(name = "deskripsi")
    public String deskripsi;

    @OneToMany(mappedBy = "barangEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "barangEntity")
    public List<ImageBarangEntity> imageBarang;

    @OneToMany(mappedBy = "barangEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"barangEntity","userAccountEntity"})
    public Set<PembelianEntity> pembelianFromBarang;

    public static Optional<BarangEntity> findBarangEntityById(Long id) {
        return find("idBarang = ? 1", id).firstResultOptional();
    }

    public static List<BarangEntity> findAllBarangEntity() {
        return BarangEntity.listAll();
    }

    public BarangEntity updateBarangEntity(BarangEntity barangEntity) {
        barangEntity.namaBarang = ManipulateUtil.changeItOrNot(namaBarang, barangEntity.namaBarang);
        barangEntity.hargaBarang = ManipulateUtil.changeItOrNot(hargaBarang, barangEntity.hargaBarang);
        barangEntity.stokBarang = ManipulateUtil.changeItOrNot(stokBarang, barangEntity.stokBarang);
        barangEntity.hargaJual = ManipulateUtil.changeItOrNot(hargaJual, barangEntity.hargaJual);
        barangEntity.unit = ManipulateUtil.changeItOrNot(unit, barangEntity.unit);
        barangEntity.deskripsi = ManipulateUtil.changeItOrNot(deskripsi, barangEntity.deskripsi);
        return barangEntity;
    }

}
