package com.sirgiyenko.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.util.Files.newFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class FileSystemDaoImplTest {

    Dao dao;
    File tempFile;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.tempFile = newFile("testJson.json");
        this.dao = new FileSystemDaoImpl(tempFile);
    }

    @AfterEach
    public void finish(){
        tempFile.delete();
    }

    @DisplayName("Method saveObjectInformation - Happy case - Writing information to file")
    @Test
    public void testCase1() {
        //Given
        String stringToFile = "This to be written to temp file";

        //When
        dao.saveObjectInformation(stringToFile);

        //Then
        String readFromTempFile = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(tempFile))){
            String line;
            while ((line = reader.readLine()) != null) {
                readFromTempFile = line;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        assertEquals(stringToFile, readFromTempFile);
    }

}