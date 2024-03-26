package org.gusanta.toserba.core.util.jackson;


import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;

import org.gusanta.toserba.core.util.FormatUtil;

public class DateDeserialize extends StdConverter<String, LocalDate> {

    @Override
    public LocalDate convert(String s) {
        return FormatUtil.convertStringToLocalDate(s);

    }
}
