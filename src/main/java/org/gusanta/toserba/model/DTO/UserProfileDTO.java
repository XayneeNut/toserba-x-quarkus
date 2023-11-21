package org.gusanta.toserba.model.DTO;

import java.time.LocalDateTime;

import org.gusanta.toserba.model.entity.UserProfileEntity;

public class UserProfileDTO {
    public Long userProfileId;
    public String patokanAlamat;
    public LocalDateTime userBirthday;
    public String userPhoto;
    public String kodePos;
    public String alamatLengkap;
    public Long userAccountEntity; // Jangan gunakan objek UserAccountEntity, cukup gunakan ID

    public UserProfileDTO(UserProfileEntity userProfileEntity) {
        this.userProfileId = userProfileEntity.userProfileId;
        this.patokanAlamat = userProfileEntity.patokanAlamat;
        this.userBirthday = userProfileEntity.userBirthday;
        this.userPhoto = userProfileEntity.userPhoto;
        this.kodePos = userProfileEntity.kodePos;
        this.alamatLengkap = userProfileEntity.alamatLengkap;

        // Inisialisasi ID UserAccountEntity jika userAccountEntity tidak null
        if (userProfileEntity.userAccountEntity != null) {
            this.userAccountEntity = userProfileEntity.userAccountEntity.userAccountId;
        }
    }
}
