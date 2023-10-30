package org.gusanta.toserba.model.body;

import java.time.LocalDateTime;

import org.gusanta.toserba.core.util.jackson.TimeDeserialize;
import org.gusanta.toserba.core.util.jackson.TimeSerialize;
import org.gusanta.toserba.model.entity.AdminAccountEntity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public record AdminAccountBody(
    Long accountId,
    String email,
    String username,
    String password,
    @JsonDeserialize(converter = TimeDeserialize.class)
    LocalDateTime createdAt,
    @JsonDeserialize(converter = TimeDeserialize.class)
    @JsonSerialize(converter = TimeSerialize.class)
    LocalDateTime updateAt
) {
    public AdminAccountEntity mapUserAccountEntity(){
        AdminAccountEntity AdminAccountEntity = new AdminAccountEntity();
        AdminAccountEntity.email = email;
        AdminAccountEntity.username = username;
        AdminAccountEntity.password = password;
        return AdminAccountEntity;
    }
}
