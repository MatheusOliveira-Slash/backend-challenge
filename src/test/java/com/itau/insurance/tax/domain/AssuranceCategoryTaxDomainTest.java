package com.itau.insurance.tax.domain;

import com.itau.insurance.tax.exception.BadRequestException;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class AssuranceCategoryTaxDomainTest {

    @Test
    public void fromValueShouldReturnEnumWhenValidValue() {
        assertTrue(AssuranceCategoryTaxDomain.fromValue("VIDA").equals(AssuranceCategoryTaxDomain.LIFE));
        assertTrue(AssuranceCategoryTaxDomain.fromValue("AUTO").equals(AssuranceCategoryTaxDomain.AUTO));
        assertTrue(AssuranceCategoryTaxDomain.fromValue("VIAGEM").equals(AssuranceCategoryTaxDomain.TRAVEL));
        assertTrue(AssuranceCategoryTaxDomain.fromValue("RESIDENCIAL").equals(AssuranceCategoryTaxDomain.RESIDENTIAL));
        assertTrue(AssuranceCategoryTaxDomain.fromValue("PATRIMONIAL").equals(AssuranceCategoryTaxDomain.PROPERTY));
    }

    @Test
    public void fromValueShouldThrowExceptionWhenInvalidValue() {
        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> AssuranceCategoryTaxDomain.fromValue("INVALID"));
    }

}
