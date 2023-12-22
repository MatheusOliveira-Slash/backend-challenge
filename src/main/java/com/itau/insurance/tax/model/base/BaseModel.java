package com.itau.insurance.tax.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.Serializers.Base;
import com.itau.insurance.tax.entity.base.BaseEntity;

public interface BaseModel<T extends BaseEntity> {

    public T toEntity();

    public BaseModel fromEntity(T entity);


    @JsonIgnore
    public boolean isValid();

}
