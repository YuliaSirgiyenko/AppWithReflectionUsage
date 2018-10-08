package com.sirgiyenko.dao;

import java.util.Map;

/**
 * Layer for information writing to long-term storage.
 */
public interface Dao {

    /**
     * Write given information to system.
     * @param map an information to store.
     * @throws Exception in case any issue occurred during storage attempt.
     */
    void saveObjectInformation(Map map);

}
