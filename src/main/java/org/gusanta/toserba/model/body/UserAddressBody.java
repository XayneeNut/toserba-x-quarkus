package org.gusanta.toserba.model.body;

import org.gusanta.toserba.model.entity.UserAddressEntity;

public record UserAddressBody(Long addressId,
        String alamatlengkap,
        String patokan,
        String posCode,
        Long userAccountEntity) {
    public UserAddressEntity mapUserAddressEntity() {
        UserAddressEntity userAddressEntity = new UserAddressEntity();
        userAddressEntity.alamatLengkap = alamatlengkap;
        userAddressEntity.patokan = patokan;
        userAddressEntity.posCode = posCode;
        return userAddressEntity;
    }
}
