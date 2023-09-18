package com.example.toesahhae.domain.user.domain.entity.enumType;



import com.example.toesahhae.common.infrastructure.domain.EnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleTypeConverter extends EnumConverter<RoleType> {
    RoleTypeConverter(){
        super(RoleType.class);
    }
}
