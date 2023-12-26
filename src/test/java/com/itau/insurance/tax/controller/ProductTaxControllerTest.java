package com.itau.insurance.tax.controller;

import com.itau.insurance.tax.model.ProductTaxModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class ProductTaxControllerTest {

    @Autowired
    ProductTaxController controller;

    @Test
    public void shouldReturn201CreatedWithValidId(){
        controller.post(new ProductTaxModel("asd", "AUTO", BigDecimal.valueOf(50)));
    }

}
