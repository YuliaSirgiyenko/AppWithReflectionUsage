package com.sirgiyenko.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileSystemDaoImpl implements Dao {

    private static final File FILE = new File("JsonVisual.json");

    public FileSystemDaoImpl(){
        if (!FILE.exists()){
            try {
                FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveObjectInformation(Map objectInfo) {
        try (FileWriter writer = new FileWriter(FILE, false)){
            writer.write(objectInfo.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
