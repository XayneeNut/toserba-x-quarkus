package org.gusanta.toserba.model.entity;

import java.util.Optional;


import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pembelian")
public class PembelianEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "gusanta_id_gen")
    @Column(name = "pembelian_id")
    public Long pembelianId;

    @ManyToOne
    @JoinColumn(name = "user")
    public UserAccountEntity userAccountEntity;

    @ManyToOne
    @JoinColumn(name = "barang")
    public BarangEntity barangEntity;

    @OneToOne
    @JoinColumn(name = "detail_pembelian")
    public DetailPembelianEntity detailPembelianEntity;

    public static Optional<PembelianEntity> findPembelianById(Long id) {
        return find("id = ? 1", id).firstResultOptional();
    }

    public static List<PembelianEntity> findAllPembelianEntities() {
        return PembelianEntity.listAll();
    }
}
