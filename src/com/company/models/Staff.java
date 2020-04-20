package com.company.models;

import com.company.utils.TimeUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class Staff {
    private List<Employee> employees = new ArrayList<>();

    public Staff() { }

    public Staff(String fileNameEmployees) {
        try {
            Scanner scanner = new Scanner(new File(fileNameEmployees));
            String line;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] data = line.split(", ");
                employees.add(new Employee(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
                        LocalDate.parse(data[2]), TimeUtils.testEndDate(data[3])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void sortEmployees() {
        Collections.sort(employees);
    }

    public void showLongestOverlappingPeriod() {
        HashMap<EmployeeKey, Long> result = getEmployeesWorkTogether();

        long maxDays = 0;
        List<EmployeeKey> maxKeys = new ArrayList<>();

        for (Map.Entry<EmployeeKey, Long> entry : result.entrySet()) {
            if (maxDays == 0 || maxDays == entry.getValue()) {
                maxDays = entry.getValue();
                maxKeys.add(entry.getKey());
            } else if (entry.getValue() > maxDays) {
                maxDays = entry.getValue();
                maxKeys.clear();
                maxKeys.add(entry.getKey());
            }
        }

        for (EmployeeKey employeeKey : maxKeys) {
            System.out.println(employeeKey.toString() + " Days: " + result.get(employeeKey));
        }
    }

    public HashMap<EmployeeKey, Long> getEmployeesWorkTogether() {
        HashMap<EmployeeKey, Long> pairsEmployees = new HashMap<>();

        int listSize = employees.size();

        for (int i = 0; i < listSize; i++) {
            Employee employeeOne = employees.get(i);
            for (int m = i + 1; m < listSize; m++) {
                Employee employeeTwo = employees.get(m);

                if (employeeOne.getEmployeeId() == employeeTwo.getEmployeeId())
                    continue;
                if (employeeOne.getProjectId() != employeeTwo.getProjectId())
                    break;
                else if (TimeUtils.checkOverlapping(employeeOne, employeeTwo)) {
                    long daysOverlapped = TimeUtils.getOverlappedDays(employeeOne, employeeTwo);

                    EmployeeKey keyEmployee = new EmployeeKey(employeeOne.getEmployeeId(), employeeTwo.getEmployeeId());
                    EmployeeKey keyEmployee1 = new EmployeeKey(employeeTwo.getEmployeeId(), employeeOne.getEmployeeId());

                    if (pairsEmployees.computeIfPresent(keyEmployee, (k, v) -> v + daysOverlapped) == null)
                        if (pairsEmployees.computeIfPresent(keyEmployee1, (k, v) -> v + daysOverlapped) == null)
                            pairsEmployees.put(keyEmployee, daysOverlapped);
                }
            }
        }
        return pairsEmployees;
    }
}
