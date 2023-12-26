package com.itau.insurance.tax.repository;

import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.entity.id.ProductTaxId;
import com.itau.insurance.tax.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductTaxRepository extends BaseRepository<ProductTaxEntity, ProductTaxId> {}
