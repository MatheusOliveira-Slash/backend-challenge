package com.itau.insurance.tax.model.base;

import com.itau.insurance.tax.entity.base.BaseEntity;

public interface BaseModel<T extends BaseEntity> {

    public T toEntity();

    public BaseModel fromEntity(T entity);


}
