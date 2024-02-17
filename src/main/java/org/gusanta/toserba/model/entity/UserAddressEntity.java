package org.gusanta.toserba.model.entity;

import java.util.List;
import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_address")
public class UserAddressEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "gusanta_id_gen")
    @Column(name = "address_id")
    public Long addressId;

    @Column(name = "address")
    public String alamatLengkap;

    @Column(name = "patokan")
    public String patokan;

    @Column(name = "pos_code")
    public String posCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id")
    public UserAccountEntity userAccountAddress;

    public static Optional<UserAddressEntity> findUserAddressEntityById(Long id) {
        return UserAddressEntity.find("id = ? 1", id).firstResultOptional();
    }

    public static List<UserAddressEntity> findAllUserAddressEntity() {
        return UserAddressEntity.listAll();
    }

}
