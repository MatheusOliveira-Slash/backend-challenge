package com.itau.insurance.tax.repository.base;

import com.itau.insurance.tax.entity.base.BaseEntity;
import com.itau.insurance.tax.entity.id.base.BaseId;
import com.itau.insurance.tax.exception.BadRequestException;
import com.itau.insurance.tax.exception.NotFoundException;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.EOFException;

public abstract class BaseRepository<T extends BaseEntity, I extends BaseId> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @SneakyThrows
    public T save(T entity){
        System.out.printf("Avua bruxo");
        throw new EOFException("");
//        return null;
    }
}
