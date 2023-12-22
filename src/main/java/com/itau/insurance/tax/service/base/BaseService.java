package com.itau.insurance.tax.service.base;

import com.itau.insurance.tax.entity.base.BaseEntity;
import com.itau.insurance.tax.entity.id.base.BaseId;
import com.itau.insurance.tax.repository.base.BaseRepository;

public abstract class BaseService<T extends BaseEntity, I extends BaseId, R extends BaseRepository<T, I>> {

    R repository;
    public BaseEntity post(BaseEntity requestBody){
        return repository.save((T) requestBody);
    }
}
