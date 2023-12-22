package com.itau.insurance.tax.controller.base;

import com.itau.insurance.tax.entity.base.BaseEntity;
import com.itau.insurance.tax.entity.id.base.BaseId;
import com.itau.insurance.tax.model.ProductTaxModel;
import com.itau.insurance.tax.model.base.BaseModel;
import com.itau.insurance.tax.repository.base.BaseRepository;
import com.itau.insurance.tax.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseController <T extends BaseEntity, I extends BaseId,M extends BaseModel<T>, R extends BaseRepository<T, I>, S extends BaseService<T, I, R>>{
    private S service;

    @PostMapping
    public ResponseEntity<BaseModel> post(@RequestBody BaseModel requestBody){

        requestBody.isValid();

        T response = (T) service.post(requestBody.toEntity());
        requestBody = requestBody.fromEntity(response);

        return ResponseEntity.status(HttpStatus.CREATED).body(requestBody);
    }


}
