package com.example.toesahhae.common.infrastructure.domain;


import com.example.toesahhae.common.exception.BusinessException;
import com.example.toesahhae.common.exception.Error;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;

public class EnumConverter<E extends Enum<E> & CodeValue> implements AttributeConverter<E, String> {

    private Class<E> clz;

    protected EnumConverter(Class<E> enumClass){
        this.clz = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return EnumSet.allOf(clz).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(()-> BusinessException.of(Error.DATA_NOT_FOUND));
    }
}
