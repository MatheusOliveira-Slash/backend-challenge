package com.itau.insurance.tax.controller;

import com.itau.insurance.tax.controller.base.BaseController;
import com.itau.insurance.tax.entity.ProductTaxEntity;
import com.itau.insurance.tax.entity.id.base.ProductTaxId;
import com.itau.insurance.tax.model.ProductTaxModel;
import com.itau.insurance.tax.repository.ProductTaxRepository;
import com.itau.insurance.tax.service.ProductTaxService;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("product/")
public class ProductTaxController extends BaseController<ProductTaxEntity, ProductTaxId, ProductTaxModel, ProductTaxRepository, ProductTaxService> {

}
