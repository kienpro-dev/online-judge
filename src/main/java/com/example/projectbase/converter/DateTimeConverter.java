package com.example.projectbase.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConverter implements Converter<Date, String> {
    private final SimpleDateFormat formatter;

    public DateTimeConverter(String pattern) {
        this.formatter = new SimpleDateFormat(pattern);
    }

    @Override
    public String convert(Date date) {
        return formatter.format(date);
    }
}
