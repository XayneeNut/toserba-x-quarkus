package org.gusanta.toserba.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.gusanta.toserba.core.util.ManipulateUtil;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_account")
public class UserAccountEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "gusanta_id_gen")
    @Column(name = "user_account_id")
    public Long userAccountId;

    @Column(name = "email")
    public String email;

    @Column(name = "password")
    public String password;

    @Column(name = "username")
    public String username;

    @CreationTimestamp
    @Column(name = "createdAt")
    public LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updateAt")
    public LocalDateTime updateAt;

    @CreationTimestamp
    @Column(name = "birthday")
    public LocalDate birthday;

    @OneToMany(mappedBy = "userAccountEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "userAccountEntity")
    public List<PembelianEntity> pembelianEntities;

    @OneToMany(mappedBy = "userAccountAddress", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "userAccountAddress")
    public List<UserAddressEntity> userAddressEntities;

    public static Optional<UserAccountEntity> findUserAccountById(Long id) {
        return find("userAccountId = ? 1", id).firstResultOptional();
    }

    public static Optional<UserAccountEntity> findUserAccountEntityByEmail(String email) {
        return find("email = ? 1", email).firstResultOptional();
    }

    public static Optional<UserAccountEntity> findUserAccountEntityByPassword(String password) {
        return find("password = ? 1", password).firstResultOptional();
    }

    public static Optional<UserAccountEntity> findUserAccountEntityByUsername(String password) {
        return find("password = ? 1", password).firstResultOptional();
    }

    public static List<UserAccountEntity> findAllUserAccountEntity() {
        return UserAccountEntity.listAll();
    }

    public UserAccountEntity updateUserAccountEntity(UserAccountEntity userAccountEntity) {
        userAccountEntity.username = ManipulateUtil.changeItOrNot(username, userAccountEntity.username);
        userAccountEntity.password = ManipulateUtil.changeItOrNot(password, userAccountEntity.password);
        userAccountEntity.updateAt = ManipulateUtil.changeItOrNot(updateAt, userAccountEntity.updateAt);
        return userAccountEntity;
    }
}
