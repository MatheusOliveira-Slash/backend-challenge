package com.itau.insurance.tax.service;

import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.entity.id.base.ProductTaxId;
import com.itau.insurance.tax.repository.ProductTaxRepository;
import com.itau.insurance.tax.service.base.BaseService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class ProductTaxService extends BaseService<ProductTaxEntity, ProductTaxId, ProductTaxRepository> {

    ProductTaxService(ProductTaxRepository repository){
        this.repository = repository;
    }

}
