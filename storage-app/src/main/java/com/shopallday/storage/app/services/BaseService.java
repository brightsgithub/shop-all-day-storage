package com.shopallday.storage.app.services;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class BaseService {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public void updateFieldsOnObject(final Map<String, Object> fields, Object object, Class clazz) {
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(clazz, key);
            field.setAccessible(true); //
            // Check if the field type is Timestamp and value is a string
            if (field.getType().equals(Timestamp.class) && value instanceof String) {
                try {
                    // Parse string date into java.sql.Timestamp
                    Date parsedDate = dateFormat.parse((String) value);
                    Timestamp timestamp = new Timestamp(parsedDate.getTime());
                    ReflectionUtils.setField(field, object, timestamp);
                } catch (Exception e) {
                    // Handle parsing exception
                    e.printStackTrace();
                }
            } else {
                ReflectionUtils.setField(field, object, value);
            }
        });
    }
}
