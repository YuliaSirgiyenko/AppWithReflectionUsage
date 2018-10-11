package com.sirgiyenko.service;

import com.sirgiyenko.annotations.CustomDateFormat;
import com.sirgiyenko.annotations.JsonValue;

import java.util.Map;

/**
 * For storing data service has to call
 * {@link com.sirgiyenko.dao.Dao#saveObjectInformation(java.lang.String)} method.
 */
public interface ObjectParser {

    /**
     * Method for receiving of information about Object (fields names and values) and writing it
     * to the system in JSON format.
     * @param o the Object will be processed.
     *
     * Demands to parsing:
     * (1)If field has annotation {@link JsonValue}, field name in
     *    JSON file should be equal to name from {@link JsonValue}.
     * (2)If field has annotation {@link CustomDateFormat}, field value in
     *    JSON file should be presented in format established by {@link CustomDateFormat}.
     * (3)If field value is null, respective field shouldn't be included in JSON.
     */
    void toJson(Object o);

    String toString(Map map);
}
