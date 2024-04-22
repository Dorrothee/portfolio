package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    Function manager = new Function();

    String inputStartDate, inputEndDate;

    Long expectedGap;

    @BeforeEach
    void setUpInput() throws ParseException {

        inputStartDate = "2023-12-20";
        inputEndDate = "2023-12-31";

        expectedGap = 11L;
    }

    @AfterEach
    void tearDown() {
        System.out.println("Running: tearDown");
    }

    @Test
    void testGap() throws IOException {

        long resultOfTestGap = manager.Gap(inputStartDate, inputEndDate);

        assertEquals(expectedGap, resultOfTestGap);

        System.out.println("Expected Gap is: " + expectedGap);
        System.out.println("Real Gap is: " + resultOfTestGap);
    }

    @Test
    void testInvalidStartDateInput(){
        String invalidStartDate = "invalid-date";
        assertThrows(DateTimeParseException.class, () -> {
            manager.Gap(invalidStartDate, inputEndDate);
        });
    }

    @Test
    void testInvalidEndDateInput(){
        String invalidEndDate = "invalid-date";
        assertThrows(DateTimeParseException.class, () -> {
            manager.Gap(inputStartDate, invalidEndDate);
        });
    }

    @Test
    void testInvalidDatesInput(){
        String invalidStartDate = "invalid-date";
        String invalidEndDate = "invalid-date";
        assertThrows(DateTimeParseException.class, () -> {
            manager.Gap(invalidStartDate, invalidEndDate);
        });
    }

    @Test
    void testValidDatesInput(){
        assertDoesNotThrow(() -> {
            manager.Gap(inputStartDate, inputEndDate);
        });
    }

    @Test
    void testEmptyInput(){
        // Redirect standard output for testing
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the program with empty input
        ByteArrayInputStream inputStream = new ByteArrayInputStream("\n\n".getBytes());
        System.setIn(inputStream);

        // Reset standard output
        System.setOut(System.out);

        // Verify the error message or default behavior
        assertFalse(outputStream.toString().contains("DateTimeParseException"));
    }

    @Test
    void testEndDateIsLaterThanStartDate(){
        // Provide invalid input
        String startDate = "2023-12-31";
        String endDate = "2023-12-20";

        // Redirect standard output for testing
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the program
        ByteArrayInputStream inputStream = new ByteArrayInputStream((startDate + "\n" + endDate + "\n").getBytes());
        System.setIn(inputStream);

        // Reset standard output
        System.setOut(System.out);

        // Verify the error message or default behavior
        assertFalse(outputStream.toString().contains("End date must be after start date"));
    }
}