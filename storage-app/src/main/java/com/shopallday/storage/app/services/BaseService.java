package com.shopallday.storage.app.services;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

public class BaseService {

    public void updateFieldsOnObject(final Map<String, Object> fields, Object object, Class clazz) {
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(clazz, key);
            field.setAccessible(true); //
            ReflectionUtils.setField(field, object, value);
        });
    }
}
