package com.sirgiyenko.dao;

import com.sirgiyenko.businessExceptions.NetworkException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSystemDaoImpl implements Dao {

    private static File FileForApp;

    public FileSystemDaoImpl(File file) throws NetworkException{
        this.FileForApp = file;
        if (!FileForApp.exists()){
            try {
                FileForApp.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                throw new NetworkException("Network Exception");
            }
        }
    }

    @Override
    public boolean saveObjectInformation(String objectData) {
        boolean flag = false;
        try (FileWriter writer = new FileWriter(FileForApp, false)){
            writer.write(objectData);
            writer.flush();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
