package org.gusanta.toserba.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.gusanta.toserba.core.util.ManipulateUtil;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
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

    @Column(name = "createdAt")
    public LocalDateTime createdAt;

    @Column(name = "updateAt")
    public LocalDateTime updateAt;

    @OneToOne(mappedBy = "userAccountEntity", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonbTransient
    @Fetch(FetchMode.JOIN)
    public UserProfileEntity userProfileEntity;

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
