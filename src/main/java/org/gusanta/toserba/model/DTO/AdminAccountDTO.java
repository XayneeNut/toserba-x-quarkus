package org.gusanta.toserba.model.DTO;

import java.time.LocalDateTime;

import org.gusanta.toserba.model.entity.AdminAccountEntity;

public class AdminAccountDTO {

    public Long accountId;
    public String email;
    public String username;
    public String password;
    public LocalDateTime createdAt;
    public LocalDateTime updateAt;

    public AdminAccountDTO(AdminAccountEntity adminAccountEntity) {
        this.accountId = adminAccountEntity.accountId;
        this.email = adminAccountEntity.email;
        this.username = adminAccountEntity.username;
        this.password = adminAccountEntity.password;
        this.createdAt = adminAccountEntity.createdAt;
        this.updateAt = adminAccountEntity.updateAt;
    }
}
