package org.gusanta.toserba.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.gusanta.toserba.core.util.ManipulateUtil;
import org.hibernate.annotations.CreationTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profile")
public class UserProfileEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    public Long userProfileId;

    @Column(name = "patokan_alamat")
    public String patokanAlamat;

    @CreationTimestamp
    @Column(name = "user_birthday")
    public LocalDateTime userBirthday;

    @Column(name = "user_photo")
    public String userPhoto;

    @Column(name = "kode_pos")
    public String kodePos;

    @Column(name = "alamat_lengkap")
    public String alamatLengkap;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_account_id")
    public UserAccountEntity userAccountEntity;

    public static Optional<UserProfileEntity> findUserProfileById(Long id) {
        return find("id = ? 1", id).firstResultOptional();
    }

    public static List<UserProfileEntity> findAllUserProfileEntity() {
        return UserProfileEntity.listAll();
    }

    public static List<UserProfileEntity> findUserProfileByAccountId(Long accountId) {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT u FROM UserProfileEntity u WHERE u.userAccountEntity.userAccountId = :accountId",
                UserProfileEntity.class)
                .setParameter("accountId", accountId)
                .getResultList();
    }

    public UserProfileEntity updateUserProfileEntity(UserProfileEntity userProfileEntity) {
        userProfileEntity.patokanAlamat = ManipulateUtil.changeItOrNot(patokanAlamat, userProfileEntity.patokanAlamat);
        userProfileEntity.userPhoto = ManipulateUtil.changeItOrNot(userPhoto, userProfileEntity.userPhoto);
        userProfileEntity.kodePos = ManipulateUtil.changeItOrNot(kodePos, userProfileEntity.kodePos);
        userProfileEntity.alamatLengkap = ManipulateUtil.changeItOrNot(alamatLengkap, userProfileEntity.alamatLengkap);
        return userProfileEntity;
    }
}
