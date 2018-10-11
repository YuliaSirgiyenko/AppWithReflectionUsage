package com.sirgiyenko.dao;

import com.sirgiyenko.businessExceptions.NetworkException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileSystemDaoImpl implements Dao {

    private static final File FILE = new File("JsonVisual.json");

    public FileSystemDaoImpl() throws NetworkException{
        if (!FILE.exists()){
            try {
                FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                throw new NetworkException("Network Exception");
            }
        }
    }

    @Override
    public boolean saveObjectInformation(String objectData) {
        boolean flag = false;
        try (FileWriter writer = new FileWriter(FILE, false)){
            writer.write(objectData);
            writer.flush();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
