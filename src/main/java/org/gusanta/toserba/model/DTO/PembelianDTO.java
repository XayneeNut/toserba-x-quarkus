package org.gusanta.toserba.model.DTO;

import org.gusanta.toserba.model.entity.PembelianEntity;

public class PembelianDTO {

    public Long pembelianId;
    public DetailPembelianDTO detailPembelianEntity;
    public AdminAccountDTO adminAccountEntity;
    public UserAccountDTO userAccountEntity;
    public UserProfileDTO userProfileEntity;

    public PembelianDTO(PembelianEntity pembelianEntity) {
        this.pembelianId = pembelianEntity.pembelianId;
        this.adminAccountEntity = new AdminAccountDTO(pembelianEntity.adminAccountEntity);
        this.userAccountEntity = new UserAccountDTO(pembelianEntity.userAccountEntity);
        this.detailPembelianEntity = new DetailPembelianDTO(pembelianEntity.detailPembelianEntity);

        // Check if userAccountEntity and userProfileEntity are not null before
        // accessing properties
        if (userAccountEntity != null && userAccountEntity.userProfileEntity != null) {
            this.userProfileEntity = new UserProfileDTO(pembelianEntity.userAccountEntity.userProfileEntity);
        } else {
            System.out.println("userProfileEntity is null or userAccountEntity is null");
            System.out.println("userAccountEntity: " + userAccountEntity);
        }
    }
}
