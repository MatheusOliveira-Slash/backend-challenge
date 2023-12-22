package com.itau.insurance.tax.controller.base;

import com.itau.insurance.tax.entity.base.BaseEntity;
import com.itau.insurance.tax.entity.id.base.BaseId;
import com.itau.insurance.tax.model.base.BaseModel;
import com.itau.insurance.tax.repository.base.BaseRepository;
import com.itau.insurance.tax.service.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class BaseController <T extends BaseEntity, I extends BaseId,M extends BaseModel<T>, R extends BaseRepository<T, I>, S extends BaseService<T, I, R>>{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected S service;

    @PostMapping
    public ResponseEntity<M> post(@RequestBody M requestBody){

        requestBody.isValid();

        T response = (T) service.post(requestBody.toEntity());
        requestBody = (M) requestBody.fromEntity(response);

        return ResponseEntity.status(HttpStatus.CREATED).body(requestBody);
    }


}
