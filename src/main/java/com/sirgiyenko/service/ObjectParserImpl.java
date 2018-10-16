package com.sirgiyenko.service;

import com.sirgiyenko.annotations.CustomDateFormat;
import com.sirgiyenko.annotations.JsonValue;
import com.sirgiyenko.businessExceptions.NetworkException;
import com.sirgiyenko.dao.Dao;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectParserImpl implements ObjectParser {

    Dao dao;

    public ObjectParserImpl(Dao dao){
        this.dao = dao;
    }

    @Override
    public void toJson(Object o) {
        Map objectFieldsAndInitValues = new LinkedHashMap<String, String>();
        Class a = o.getClass();
        Field fields[] = a.getDeclaredFields();
        String fieldName;
        Object fieldValue = null;

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            fieldName = getFieldName(fields[i]);
            try {
                fieldValue = returnFieldValue(fields[i], o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            objectFieldsAndInitValues.put(fieldName, fieldValue);
        }

        try {
            dao.saveObjectInformation(toString(objectFieldsAndInitValues));
        } catch (NetworkException e) {
            System.out.println("Network Exception");
        }
    }

    private String getFieldName(Field field){
        String fieldName;
        if (field.isAnnotationPresent(JsonValue.class)) {
            fieldName = field.getAnnotation(JsonValue.class).name();
        } else {
            fieldName = field.getName();
        }
        return fieldName;
    }

    private Object returnFieldValue(Field field, Object o) throws IllegalAccessException {
        if (field.get(o) != null) {
            return returnFieldValueInStringIfNotNull(field, o);
        } else {
            return null;
        }
    }

    private String returnFieldValueInStringIfNotNull(Field field, Object o) throws IllegalAccessException {
        String fieldValue = null;
        if (field.isAnnotationPresent(CustomDateFormat.class)) {
            String ownFormat = field.getAnnotation(CustomDateFormat.class).format();
            fieldValue = ((LocalDate) field.get(o)).format(DateTimeFormatter.ofPattern(ownFormat));
        } else {
            fieldValue = field.get(o).toString();
        }
        return fieldValue;
    }

    @Override
    public String toString (Map mapToString){
        if (allMapValuesAreNull(mapToString)) {
            return "{}";
        } else {
            String resutledString = "{";
            for (Object key : mapToString.keySet()) {
                if (mapToString.get(key) != null) {
                    resutledString = resutledString + "\"" + key + "\": \"" + mapToString.get(key) + "\", ";
                }
            }
            return resutledString.substring(0, resutledString.length() - 2) + "}";
        }
    }

    private boolean allMapValuesAreNull(Map map){
        boolean flag = true;
        for (Object key : map.keySet()) {
            if (map.get(key) != null) {
                flag = false;
            }
        }
        return flag;
    }

}
