package com.sky.tv.comics.service.validation;

import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class CrudBusinessValidator extends Validator {

    public <K, E> void validate(Set<K> keys, Set<E> entities) {
        validate(() ->  keys.size() == entities.size(), "Objects don't exist");
    }

    public <E> void validate(Set<E> entities) {
        validate(entities::isEmpty, "Objects exist");
    }
}
