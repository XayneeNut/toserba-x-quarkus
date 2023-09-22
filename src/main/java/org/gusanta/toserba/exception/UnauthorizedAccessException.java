package org.gusanta.toserba.exception;

/**
 * Gunakan Exeption ini jika users tidak diizinkan masuk. Contoh : Password
 * salah.
 */
public class UnauthorizedAccessException extends ServiceBaseException {

	private static final long serialVersionUID = 8465116975507296397L;

    public UnauthorizedAccessException(ExceptionCode code) {
        super(code);
    }

    public UnauthorizedAccessException(ExceptionCode code, String message) {
        super(code, message);
    }
}
