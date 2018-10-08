package com.sirgiyenko.service;

import com.sirgiyenko.dao.Dao;
import com.sirgiyenko.dao.FileSystemDaoImpl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectParserImpl implements ObjectParser {

    Dao dao = new FileSystemDaoImpl();
    Map objectFieldsAndInitValues = new HashMap<String, String>();

    @Override
    public void toJson(Object o) {
        Class a = o.getClass();
        Field fields[] = a.getDeclaredFields();
        Object fieldValue = new Object();

        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
            if(!fields[i].isAccessible()){
                fields[i].setAccessible(true);
                try{
                    fieldValue = fields[i].get(o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            objectFieldsAndInitValues.put(fields[i].getName(), fieldValue);
        }

        dao.saveObjectInformation(objectFieldsAndInitValues);
    }

}
