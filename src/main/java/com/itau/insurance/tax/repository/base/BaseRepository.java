package com.itau.insurance.tax.repository.base;

import com.itau.insurance.tax.domain.AssuranceCategoryDomain;
import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.entity.base.BaseEntity;
import com.itau.insurance.tax.entity.id.base.BaseId;
import com.itau.insurance.tax.exception.BadRequestException;
import com.itau.insurance.tax.exception.NotFoundException;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.EOFException;
import java.math.BigDecimal;

public abstract class BaseRepository<T extends BaseEntity, I extends BaseId> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public T save(T entity){
        return (T) new ProductTaxEntity("id", "nome", AssuranceCategoryDomain.LIFE, BigDecimal.valueOf(1.0), BigDecimal.valueOf(1.1));
    }
}
