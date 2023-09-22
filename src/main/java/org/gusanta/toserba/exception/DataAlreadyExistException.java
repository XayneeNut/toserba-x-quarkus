package org.gusanta.toserba.exception;

/**
 * Gunakan exception ini jika data sudah tersedia di database.
 *
 * @author Hariyogi
 * @since 2 Sep 2020
 */
public class DataAlreadyExistException extends ServiceBaseException {
    private static final long serialVersionUID = 2584965928048952730L;

    public DataAlreadyExistException(ExceptionCode code) {
        super(code);
    }

    public DataAlreadyExistException(ExceptionCode code, String param) {
        super(code, param);
    }
}
