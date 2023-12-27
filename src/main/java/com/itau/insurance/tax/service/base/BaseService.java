package com.itau.insurance.tax.service.base;

import com.itau.insurance.tax.entity.base.BaseEntity;
import com.itau.insurance.tax.entity.id.base.BaseId;
import com.itau.insurance.tax.exception.NotFoundException;
import com.itau.insurance.tax.repository.base.BaseRepository;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class BaseService<E extends BaseEntity<I>, I extends BaseId, R extends BaseRepository<E, I>> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected R repository;

    public E post(E requestBody){
        return repository.save(requestBody);
    }

    public E insertOrUpdate(I id, E entity) {

        Optional<E> databaseEntity = repository.findById(id);

        if (!databaseEntity.isPresent()) {
            entity.setInsertedAt(LocalDateTime.now());
        } else {
            entity.setId(databaseEntity.get().getId());
            entity.setInsertedAt(databaseEntity.get().getInsertedAt() != null ?
                    databaseEntity.get().getInsertedAt() : databaseEntity.get().getUpdatedAt());
        }

        return repository.save(entity);

    }

    @SneakyThrows
    public E update(I id, @Valid E requestEntity) {
        Optional<E> databaseEntity = repository.findById(id);
        if (databaseEntity.isPresent()) {
            databaseEntity.get().patch(requestEntity);
            return repository.save(databaseEntity.get());
        } else {
            throw new NotFoundException("Registro não encontrado", id.toString());
        }
    }

    public List<E> findAll() {
        return repository.findAll();
    }

    public Optional<E> findById(I id) {
        return repository.findById(id);
    }

}
