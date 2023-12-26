package com.itau.insurance.tax.entity.id.base;

import jakarta.persistence.Embeddable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;


@Embeddable
public abstract class BaseId {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                String fieldName = field.getName();
                Object fieldValue = field.get(this);

                result.append(fieldName).append(": ").append(fieldValue).append(", ");
            } catch (IllegalAccessException e) {
                logger.error("Error parsing element: {}", e.getMessage());
            }
        }

        if (result.length() > 0) {
            result.setLength(result.length() - 2);
        }

        return result.toString();
    }

}
