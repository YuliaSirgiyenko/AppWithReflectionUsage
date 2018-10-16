package com.sirgiyenko.service;

import com.sirgiyenko.businessExceptions.NetworkException;
import com.sirgiyenko.dao.Dao;
import com.sirgiyenko.models.Human;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ObjectParserImplTest {

    private ObjectParserImpl objectParser;

    @Mock
    public Dao mockedDao;
    @Captor
    ArgumentCaptor<String> argumentCapture;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.objectParser = new ObjectParserImpl(mockedDao);
    }


    @DisplayName("Method toJson - Happy case1 - Parse of object fields/values to String as Json format")
    @Test
    public void testCase1() {
        //Given
        Human mockedHuman = new Human("Human's First Name", "Human's Last name",
                "Human's Hobby", LocalDate.of(2000, 1, 1));
        String expectedLineJsonFormat = "{\"firstName\": \"Human's First Name\", \"lastName\":" +
                " \"Human's Last name\", \"fun\": \"Human's Hobby\", \"birthDate\": \"01-01-2000\"}";

        //When
        objectParser.toJson(mockedHuman);

        //Then
        verify(mockedDao).saveObjectInformation(argumentCapture.capture());
        assertThat(argumentCapture.getValue()).isEqualTo(expectedLineJsonFormat);
    }

    @DisplayName("Method toJson - Happy case2 - Parse (exclude null values) of object" +
            "fields/values to String as Json format")
    @Test
    public void testCase2() {
        //Given
        Human mockedHuman = new Human("Human's First Name", null,
                "Human's Hobby", LocalDate.of(2000, 1, 1));
        String expectedLineJsonFormat = "{\"firstName\": \"Human's First Name\", " +
                "\"fun\": \"Human's Hobby\", \"birthDate\": \"01-01-2000\"}";

        //When
        objectParser.toJson(mockedHuman);

        //Then
        verify(mockedDao).saveObjectInformation(argumentCapture.capture());
        assertThat(argumentCapture.getValue()).isEqualTo(expectedLineJsonFormat);
    }

    @DisplayName("Method toJson - Happy case3 - Parse of object fields/values to String as Json format" +
            "all values are NULL")
    @Test
    public void testCase3() {
        //Given
        Human mockedHuman = new Human(null, null,
                null, null);
        String expectedLineJsonFormat = "{}";

        //When
        objectParser.toJson(mockedHuman);

        //Then
        verify(mockedDao).saveObjectInformation(argumentCapture.capture());
        assertThat(argumentCapture.getValue()).isEqualTo(expectedLineJsonFormat);
    }

    @DisplayName("Method toJson - fail during storage")
    @Test
    public void testCase4() {
        //Given
        Human mockedHuman = new Human("Human's First Name", "Human's Last name",
                "Human's Hobby", LocalDate.of(2000, 1, 1));
        String mockedExpectedLineJsonFormat = "{\"firstName\": \"Human's First Name\", \"lastName\":" +
                " \"Human's Last name\", " + "\"hobby\": \"Human's Hobby\", \"birthDate\": \"2000-01-01\"}";

        when(mockedDao.saveObjectInformation(mockedExpectedLineJsonFormat)).
                thenThrow(new NetworkException("Network Exception"));

        //When
        objectParser.toJson(mockedHuman);
    }

}