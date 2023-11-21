package org.gusanta.toserba.model.body;

import java.time.LocalDateTime;

import org.gusanta.toserba.core.util.jackson.TimeDeserialize;
import org.gusanta.toserba.model.entity.UserProfileEntity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record UserProfileBody(
        Long userProfileId,
        String patokanAlamat,
        @JsonDeserialize(converter = TimeDeserialize.class) LocalDateTime userBirthday,
        String userPoto,
        String kodePos,
        String alamatLengkap,
        Long userAccountId) {
    public UserProfileEntity mapUserProfileEntity() {
        var userProfileEntity = new UserProfileEntity();
        userProfileEntity.patokanAlamat = patokanAlamat;
        userProfileEntity.userPhoto = userPoto;
        userProfileEntity.kodePos = kodePos;
        userProfileEntity.alamatLengkap = alamatLengkap;
        return userProfileEntity;
    }
}