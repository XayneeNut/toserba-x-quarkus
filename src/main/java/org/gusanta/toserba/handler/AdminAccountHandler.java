package org.gusanta.toserba.handler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.gusanta.toserba.exception.response.MessageResponse;
import org.gusanta.toserba.model.body.AdminAccountBody;
import org.gusanta.toserba.model.entity.AdminAccountEntity;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class AdminAccountHandler {

    public AdminAccountEntity getAdminAccountEntityById(Long id) {
        return AdminAccountEntity.findAdminAccountById(id).orElseThrow(() -> MessageResponse.idNotFoundException(id));
    }

    public AdminAccountEntity getAdminAccountEntityByEmail(String email) {
        return AdminAccountEntity.findAdminAccountByEmail(email)
                .orElseThrow(() -> MessageResponse.idNotFoundException(email));
    }

    public AdminAccountEntity getAdminAccountEntityByUsername(String username) {
        return AdminAccountEntity.findAdminAccountByUsername(username)
                .orElseThrow(() -> MessageResponse.idNotFoundException(username));
    }

    public AdminAccountEntity getAdminAccountEntityByPassword(String password) {
        return AdminAccountEntity.findAdminAccountByPassword(password)
                .orElseThrow(() -> MessageResponse.idNotFoundException(password));
    }

    public List<AdminAccountEntity> getAllAdminAccountEntity() {
        return AdminAccountEntity.findAllAdminAccountEntity().stream().collect(Collectors.toList());
    }

    public AdminAccountEntity getAdminAccountEntityByEmailAndPassword(String email, String password) {
        var adminAccountEntity = getAdminAccountEntityByEmail(email);
        var encryptedPasswordFromDb = adminAccountEntity.password;
        boolean passwordMatch = BCrypt.checkpw(password, encryptedPasswordFromDb);
        if (!passwordMatch) {
            throw MessageResponse.failedToFindDataException(password, "password");
        } else if (!adminAccountEntity.email.equals(email)) {
            throw MessageResponse.failedToFindDataException(email, "email");
        }
        return adminAccountEntity;
    }

    public AdminAccountEntity createAdminAccountEntity(AdminAccountBody adminAccountBody) {
        Objects.requireNonNull(adminAccountBody);
        var email = adminAccountBody.email();
        var password = adminAccountBody.password();
        var username = adminAccountBody.username();
        var adminAccountMap = adminAccountBody.mapUserAccountEntity();
        if (AdminAccountEntity.findAdminAccountByEmail(email).isPresent()) {
            throw MessageResponse
            .failedToFindDataException(email, "email", "email is present");
        } else if (AdminAccountEntity.findAdminAccountByUsername(username).isPresent()) {
            throw MessageResponse
            .failedToFindDataException(username, "username", "Username is present");
        }
        String encryptedEmail = BCrypt.hashpw(email, BCrypt.gensalt());
        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        adminAccountMap.email = encryptedEmail;
        adminAccountMap.password = encryptedPassword;
        adminAccountMap.persist();
        return adminAccountMap;
    }

    public Response deleteAdminAccountEntityById(Long id){
        if(getAdminAccountEntityById(id) != null){
            AdminAccountEntity.deleteById(id);
            return MessageResponse.deleteSucces(id);
        }else {
            return MessageResponse.idNotFound(id);
        }
    }
}