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
    private Map objectFieldsAndInitValues = new LinkedHashMap<String, String>();

    public ObjectParserImpl(Dao dao){
        this.dao = dao;
    }

    @Override
    public void toJson(Object o) {
        Class a = o.getClass();
        Field fields[] = a.getDeclaredFields();
        String fieldName;
        Object fieldValue;

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if (fields[i].isAnnotationPresent(JsonValue.class)) {
                fieldName = fields[i].getAnnotation(JsonValue.class).name();
            } else {
                fieldName = fields[i].getName();
            }

            fieldValue = null;
            try {
                if (fields[i].get(o) != null) {
                    if (fields[i].isAnnotationPresent(CustomDateFormat.class)) {
                        String ownFormat = fields[i].getAnnotation(CustomDateFormat.class).format();
                        fieldValue = ((LocalDate) fields[i].get(o)).format(DateTimeFormatter.ofPattern(ownFormat));
                    } else {
                        fieldValue = fields[i].get(o).toString();
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            objectFieldsAndInitValues.put(fieldName, fieldValue);
            }

            String stringFromMap = toString(objectFieldsAndInitValues);
            try {
                dao.saveObjectInformation(stringFromMap);
            } catch (NetworkException e) {
                System.out.println("Network Exception");
            }
        }

        @Override
        public String toString (Map mapToString){
            String resutledString = "{";

            for (Object key : mapToString.keySet()) {
                if (mapToString.get(key) != null) {
                    resutledString = resutledString + "\"" + key + "\": \"" + mapToString.get(key) + "\", ";
                }
            }

            resutledString = resutledString.substring(0, resutledString.length() - 2);
            return resutledString + "}";
        }


}
