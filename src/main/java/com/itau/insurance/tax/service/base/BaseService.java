package com.itau.insurance.tax.service.base;

import com.itau.insurance.tax.entity.base.BaseEntity;
import com.itau.insurance.tax.entity.id.base.BaseId;
import com.itau.insurance.tax.repository.base.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public abstract class BaseService<T extends BaseEntity, I extends BaseId, R extends BaseRepository<T, I>> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected R repository;

    public T post(T requestBody){
        return repository.save(requestBody);
    }
}
