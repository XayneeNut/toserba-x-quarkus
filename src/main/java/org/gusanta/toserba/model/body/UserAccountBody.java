package org.gusanta.toserba.model.body;

import java.time.LocalDateTime;

import org.gusanta.toserba.core.util.jackson.TimeDeserialize;
import org.gusanta.toserba.core.util.jackson.TimeSerialize;
import org.gusanta.toserba.model.entity.UserAccountEntity;
import org.gusanta.toserba.model.entity.UserProfileEntity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public record UserAccountBody(
        Long userAccountId,
        String email,
        String password,
        String username,
        @JsonDeserialize(converter = TimeDeserialize.class) LocalDateTime createdAt,
        @JsonDeserialize(converter = TimeDeserialize.class) @JsonSerialize(converter = TimeSerialize.class) LocalDateTime updateAt,
        Long userProfileEntity,
        UserProfileEntity userProfile) {

    public UserAccountEntity mapUserAccountEntity() {
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.email = email;
        userAccountEntity.password = password;
        userAccountEntity.username = username;
        return userAccountEntity;
    }
}
