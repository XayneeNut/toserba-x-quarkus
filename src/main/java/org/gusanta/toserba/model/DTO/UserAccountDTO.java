package org.gusanta.toserba.model.DTO;

import java.time.LocalDateTime;

import org.gusanta.toserba.model.entity.UserAccountEntity;

public class UserAccountDTO {
    public Long userAccountId;
    public String email;
    public String password;
    public String username;
    public LocalDateTime createdAt;
    public LocalDateTime updateAt;
    public String image;
    public UserProfileDTO userProfileEntity; // Gunakan DTO untuk UserProfileEntity

    public UserAccountDTO(UserAccountEntity userAccountEntity) {
        this.userAccountId = userAccountEntity.userAccountId;
        this.email = userAccountEntity.email;
        this.password = userAccountEntity.password;
        this.username = userAccountEntity.username;
        this.createdAt = userAccountEntity.createdAt;
        this.updateAt = userAccountEntity.updateAt;
        this.image = userAccountEntity.image;

        if (userAccountEntity.userProfileEntity != null) {
            this.userProfileEntity = new UserProfileDTO(userAccountEntity.userProfileEntity);
        }
    }
}
