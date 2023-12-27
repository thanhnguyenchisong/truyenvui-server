package com.sky.tv.comics.service.validation;

import com.sky.tv.comics.exception.BusinessException;
import java.util.function.BooleanSupplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public abstract class Validator {

    protected void validate(BooleanSupplier validator, String message) {
        if (!validator.getAsBoolean()) {
            log.error(message);
            throw new BusinessException(message);
        }
    }
}
