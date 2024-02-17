package org.gusanta.toserba.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_account")
public class AdminAccountEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "gusanta_id_gen")
    @Column(name = "account_id")
    public Long accountId;

    @Column(name = "email")
    public String email;

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;

    @Column(name = "createdAt")
    public LocalDateTime createdAt;

    @Column(name = "updateAt")
    public LocalDateTime updateAt;

    public static Optional<AdminAccountEntity> findAdminAccountById(Long id) {
        return find("id = ? 1", id).firstResultOptional();
    }

    public static Optional<AdminAccountEntity> findAdminAccountByEmail(String email) {
        return find("email = ? 1", email).firstResultOptional();
    }

    public static Optional<AdminAccountEntity> findAdminAccountByPassword(String password) {
        return find("password = ? 1", password).firstResultOptional();
    }

    public static Optional<AdminAccountEntity> findAdminAccountByUsername(String username) {
        return find("username = ? 1", username).firstResultOptional();
    }

    public static List<AdminAccountEntity> findAllAdminAccountEntity() {
        return AdminAccountEntity.listAll();
    }

}
