package com.sirgiyenko.dao;

import com.sirgiyenko.businessExceptions.NetworkException;

import java.util.Map;

/**
 * Layer for information writing to long-term storage.
 */
public interface Dao {

    /**
     * Write given information to system.
     * @param stringData an information to store.
     * @throws NetworkException in case any issue occurred during storage attempt.
     */
    boolean saveObjectInformation(String stringData);

}
