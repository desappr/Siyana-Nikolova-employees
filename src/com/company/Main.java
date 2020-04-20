package com.company;

import com.company.models.Staff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter filepath: ");
        //"src/com/company/data.txt"
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        Staff staff = new Staff(fileName);
        staff.sortEmployees();
        staff.showLongestOverlappingPeriod();
    }
}
