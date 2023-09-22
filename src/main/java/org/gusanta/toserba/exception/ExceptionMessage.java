package org.gusanta.toserba.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Pesan exception sederhana,
 *
 * @author Hariyogi
 * @since 2 Sep 2020
 */
@Data
@AllArgsConstructor
public class ExceptionMessage {
    private final String timestamp = LocalDateTime.now().toString();
    private String httpCode;
    private String message;
}
