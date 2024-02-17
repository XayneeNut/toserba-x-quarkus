package org.gusanta.toserba.handler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.gusanta.toserba.exception.response.MessageResponse;
import org.gusanta.toserba.model.body.UserAddressBody;
import org.gusanta.toserba.model.entity.UserAccountEntity;
import org.gusanta.toserba.model.entity.UserAddressEntity;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserAddressHandler {

    public List<UserAddressEntity> getAllUserAddressEntities() {
        return UserAddressEntity.findAllUserAddressEntity().stream().collect(Collectors.toList());
    }

    public UserAddressEntity getUserAddressEntityById(Long id) {
        return UserAddressEntity.findUserAddressEntityById(id)
                .orElseThrow(() -> MessageResponse.idNotFoundException(id));
    }

    private UserAccountEntity fetchUserAccountEntity(Long id) {
        return UserAccountEntity.findUserAccountById(id)
                .orElseThrow(() -> MessageResponse.fetchMessageException(id, "userAccountEntity"));
    }

    private UserAddressEntity checkingWithCreate(UserAddressBody userAddressBody, UserAccountEntity userAccountEntity){
        var userAddress = userAddressBody.mapUserAddressEntity();
        userAddress.userAccountAddress = userAccountEntity;
        userAddress.persist();
        return userAddress;
    }

    public UserAddressEntity createUserAddress(UserAddressBody userAddressBody){
        Objects.requireNonNull(userAddressBody);
        var userAccountEntity = fetchUserAccountEntity(userAddressBody.userAccountEntity());
        return checkingWithCreate(userAddressBody, userAccountEntity);
    }
}
