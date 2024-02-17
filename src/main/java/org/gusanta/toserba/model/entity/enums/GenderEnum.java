package org.gusanta.toserba.model.entity.enums;

import lombok.Getter;

public enum GenderEnum {
    PRIA(0),
    WANITA(1),
    PRIVASI(-1);

    @Getter
    public final int code;

    GenderEnum(int code) {
        this.code = code;
    }

    public static GenderEnum getGender(int code) {
        return switch (code) {
            case 0 -> GenderEnum.PRIA;
            case 1 -> GenderEnum.WANITA;
            default -> GenderEnum.PRIVASI;
        };
    }
}
