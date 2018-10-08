package com.sirgiyenko.service;

/**
 * For storing data service has to call
 * {@link com.sirgiyenko.dao.Dao#saveObjectInformation(java.util.Map)} method.
 */
public interface ObjectParser {

    /**
     * Method for receiving of information about Object (fields names and values) and writing it to the system.
     * @param o the Object will be processed.
     */
    void toJson(Object o);

}
