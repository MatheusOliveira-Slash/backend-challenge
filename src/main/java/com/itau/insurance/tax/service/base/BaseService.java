package com.itau.insurance.tax.service.base;

import com.itau.insurance.tax.entity.base.BaseEntity;
import com.itau.insurance.tax.entity.id.base.BaseId;
import com.itau.insurance.tax.repository.base.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<E extends BaseEntity<I>, I extends BaseId, R extends BaseRepository<E, I>> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected R repository;

    public E post(E requestBody){
        return repository.save(requestBody);
    }

}
