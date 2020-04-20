package com.company.models;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Employee implements Comparable {
    private int employeeId;
    private int projectId;
    private LocalDate startDate;
    private LocalDate endDate;
    private long workDays;

    public Employee(int employeeId, int projectId, LocalDate startDate, LocalDate endDate) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.workDays = DAYS.between(startDate, endDate);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getWorkDays() {
        return workDays;
    }

    public void setWorkDays(long workDays) {
        this.workDays = workDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return this.employeeId == employee.employeeId;
    }

    @Override
    public int compareTo(Object o) {
       return this.projectId - ((Employee) o).getProjectId();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", projectId=" + projectId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", workDays=" + workDays +
                '}';
    }
}