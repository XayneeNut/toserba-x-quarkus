package org.gusanta.toserba.core.util;

import lombok.Getter;

public enum TableStatus {
    DRAFT(0),
    ACTIVE(1),
    DEACTIVATED(2),
    REPLACED(3),
    ALL(-2),
    ERROR(-1);

    @Getter
    private final int code;

    public static TableStatus getStatus(int code) {
        switch (code) {
            case 0:
                return DRAFT;
            case 1:
                return ACTIVE;
            case 2:
                return DEACTIVATED;
            case 3:
                return REPLACED;
            default:
                return ERROR;
        }
    }

    TableStatus(int code) {
        this.code = code;
    }
}
