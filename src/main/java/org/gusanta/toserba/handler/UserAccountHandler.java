package org.gusanta.toserba.handler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.gusanta.toserba.exception.response.MessageResponse;
import org.gusanta.toserba.model.entity.UserAccountEntity;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

import org.gusanta.toserba.model.body.UserAccountBody;

@ApplicationScoped
public class UserAccountHandler {
    public UserAccountEntity getUserAccountEntityById(Long id) {
        return UserAccountEntity.findUserAccountById(id)
                .orElseThrow(() -> MessageResponse.idNotFoundException(id));
    }

    public UserAccountEntity getUserAccountEntityByEmail(String email) {
        return UserAccountEntity.findUserAccountEntityByEmail(email)
                .orElseThrow(() -> MessageResponse.idNotFoundException(email));
    }

    public UserAccountEntity getUserAccountEntityByUsername(String username) {
        return UserAccountEntity.findUserAccountEntityByUsername(username)
                .orElseThrow(() -> MessageResponse.idNotFoundException(username));
    }

    public UserAccountEntity getUserAccountEntityByPassword(String password) {
        return UserAccountEntity.findUserAccountEntityByPassword(password)
                .orElseThrow(() -> MessageResponse.idNotFoundException(password));
    }

    public UserAccountEntity getUserAccountEntityByEmailAndPassword(String email, String password) {
        var userAccountEntity = getUserAccountEntityByEmail(email);
        var hashedPasswordFromDatabase = userAccountEntity.password;
        boolean passwordMatch = BCrypt.checkpw(password, hashedPasswordFromDatabase);
        if (!passwordMatch) {
            throw MessageResponse.failedToFindDataException(password, "password");
        } else if (!userAccountEntity.email.equals(email)) {
            throw MessageResponse.failedToFindDataException(email, "email");
        }
        return userAccountEntity;
    }

    public List<UserAccountEntity> getAllUserAccountEntities() {
        return UserAccountEntity.findAllUserAccountEntity()
                .stream()
                .collect(Collectors.toList());
    }

    public UserAccountEntity createUserAccountEntity(UserAccountBody userAccountBody) {
        Objects.requireNonNull(userAccountBody);
        var email = userAccountBody.email();
        var password = userAccountBody.password();
        var username = userAccountBody.username();
        var userAccountMap = userAccountBody.mapUserAccountEntity();

        if (UserAccountEntity.findUserAccountEntityByEmail(email).isPresent()) {
            throw MessageResponse.failedToFindDataException(email, "email", "username is on used");
        } else if (UserAccountEntity.findUserAccountEntityByUsername(username).isPresent()) {
            throw MessageResponse.failedToFindDataException(username, "username", "username is on used");
        }
        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        userAccountMap.password = encryptedPassword;
        userAccountMap.persist();
        return userAccountMap;
    }

    public Response deleteUserAccountEntityById(Long id) {
        if (getUserAccountEntityById(id) != null) {
            UserAccountEntity.deleteById(id);
            return MessageResponse.deleteSucces(id);
        } else {
            return MessageResponse.idNotFound(id);
        }
    }
}
