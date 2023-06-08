package com.example.RigaPskov.utils;

import com.example.RigaPskov.entities.Request;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TypeConverter implements AttributeConverter<Request.Type, String> {

    @Override
    public String convertToDatabaseColumn(Request.Type type) {
        if (type == null) {
            return null;
        }
        return type.getValue();
    }

    @Override
    public Request.Type convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        return Request.Type.fromValue(value);
    }
}
