package com.itau.insurance.tax.repository.base;

import com.itau.insurance.tax.entity.base.BaseEntity;
import com.itau.insurance.tax.entity.id.base.BaseId;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseRepository<T extends BaseEntity, I extends BaseId> {
    public T save(T entity){
        System.out.printf("Avua bruxo");
        return null;
    }
}
