package org.gusanta.toserba.handler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.gusanta.toserba.exception.response.MessageResponse;
import org.gusanta.toserba.model.body.UserProfileBody;
import org.gusanta.toserba.model.entity.UserAccountEntity;
import org.gusanta.toserba.model.entity.UserProfileEntity;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserProfileHandler {

    public UserProfileEntity getUserProfileEntityById(Long id) {
        return UserProfileEntity.findUserProfileById(id).orElseThrow(() -> MessageResponse.idNotFoundException(id));
    }

    public List<UserProfileEntity> getAllUserProfileEntity() {
        return UserProfileEntity.findAllUserProfileEntity().stream().collect(Collectors.toList());
    }

    public List<UserProfileEntity> getAllUserProfileEntitiesByAccountId() {
        return UserProfileEntity.findAllUserProfileEntity().stream().collect(Collectors.toList());
    }

    public UserAccountEntity fetchUserAccountEntity(Long id) {
        return UserAccountEntity.findUserAccountById(id).orElseThrow(() -> MessageResponse.idNotFoundException(id));
    }

    public UserProfileEntity checkingWithCreate(UserProfileBody userProfileBody, UserAccountEntity userAccountEntity) {
        var user = userProfileBody.mapUserProfileEntity();
        user.userAccountEntity = userAccountEntity;
        user.persist();
        return user;
    }

    public UserProfileEntity createUserProfile(UserProfileBody userProfileBody) {
        Objects.requireNonNull(userProfileBody);
        var userAccountEntity = fetchUserAccountEntity(userProfileBody.userAccountId());
        if (userAccountEntity.isPersistent()) {
            throw MessageResponse.isPersistenException(userAccountEntity.userAccountId, "userAccountId",
                    "Satu user hanya bisa memiliki 1 profile");
        }
        return checkingWithCreate(userProfileBody, userAccountEntity);
    }

}
