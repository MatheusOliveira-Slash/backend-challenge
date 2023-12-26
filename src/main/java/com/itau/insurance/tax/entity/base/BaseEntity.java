package com.itau.insurance.tax.entity.base;

import com.itau.insurance.tax.entity.id.base.BaseId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<I extends BaseId> {

    @EmbeddedId
    private I id;

    private LocalDateTime insertedAt;
    private LocalDateTime updatedAt = LocalDateTime.now();


}
