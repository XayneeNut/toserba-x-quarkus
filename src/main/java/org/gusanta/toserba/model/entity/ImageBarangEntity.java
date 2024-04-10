package org.gusanta.toserba.model.entity;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Setter;

@Entity
@Setter
@Table(name = "image_barang")
@JsonIgnoreProperties(value = "barangEntity")
public class ImageBarangEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "gusanta_id_gen")
    @Column(name = "image_barang_id")
    public Long gambarId;

    @Column(name = "image", columnDefinition = "blob")
    public String gambar;

    @ManyToOne
    @JoinColumn(name = "barang_id")

    public BarangEntity barangEntity;

    public static Optional<ImageBarangEntity> findImageEntityById(Long id) {
        return find("id = ? 1", id).firstResultOptional();
    }

    public static List<ImageBarangEntity> findAllImageEntity() {
        return ImageBarangEntity.listAll();
    }
}
