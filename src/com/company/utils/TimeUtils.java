package com.company.utils;

import com.company.models.Employee;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class TimeUtils {
    public static LocalDate testEndDate(String date) {
        if (date.equals("NULL"))
            return LocalDate.now();
        else
            return LocalDate.parse(date);
    }

    //checks if employees' day periods overlap
    public static boolean checkOverlapping(Employee employeeOne, Employee employeeTwo) {
        return !employeeOne.getStartDate().isAfter(employeeTwo.getEndDate())
                && !employeeTwo.getStartDate().isAfter(employeeOne.getEndDate());
    }

    //returns the number of days of overlapping
    public static long getOverlappedDays(Employee employeeOne, Employee employeeTwo) {
        long minWorkDays = Math.min(employeeOne.getWorkDays(), employeeTwo.getWorkDays());

        //min(end1 - start2, end2 - start1)
        long minOverlapDays = Math.min(DAYS.between(employeeTwo.getStartDate(), employeeOne.getEndDate()),
                DAYS.between(employeeOne.getStartDate(), employeeTwo.getEndDate()));

        return Math.min(minWorkDays, minOverlapDays);
    }
}
