package org.gusanta.toserba.core.util.jackson;

import java.time.LocalDateTime;

import org.gusanta.toserba.core.util.FormatUtil;

import com.fasterxml.jackson.databind.util.StdConverter;

public class TimeDeserialize extends StdConverter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String s) {
        return FormatUtil.convertToLocalDate(s);
    }
}
