package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Function {

    public long Gap(String startDateInput, String endDateInput) throws IOException {

        // Define two LocalDate objects representing the start and end dates
        // Parse user input into LocalDate objects
        LocalDate startDate = LocalDate.parse(startDateInput, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDate = LocalDate.parse(endDateInput, DateTimeFormatter.ISO_LOCAL_DATE);

        // Calculate the number of days between the two dates
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        // Display the result
        System.out.println("\nNumber of days between " + startDate + " and " + endDate + " is " + daysBetween);

        return daysBetween;
    }


}
