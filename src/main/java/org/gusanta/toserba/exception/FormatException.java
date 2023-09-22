package org.gusanta.toserba.exception;

/**
 * Gunakan exception ini jika terdapat format yang salah.
 *
 * @author Hariyogi
 * @since 2 Sep 2020
 */
public class FormatException extends ServiceBaseException {

    private static final long serialVersionUID = 8990164132805617586L;

    public FormatException(String message) {
        super(ExceptionCode.F_NV, message);
    }

    public FormatException(ExceptionCode code, String message) {
        super(code, message);
    }

    public FormatException(ExceptionCode code) {
        super(code);
    }
}
