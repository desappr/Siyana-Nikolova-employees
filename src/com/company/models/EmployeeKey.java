package com.company.models;

public class EmployeeKey {
    public final int employeeOne;
    public final int employeeTwo;

    public EmployeeKey(int employeeOne, int employeeTwo) {
        this.employeeOne = employeeOne;
        this.employeeTwo = employeeTwo;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof EmployeeKey)) return false;
        if(this.employeeOne  == ((EmployeeKey) o).employeeOne || this.employeeTwo  == ((EmployeeKey) o).employeeOne)
            return this.employeeTwo == ((EmployeeKey) o).employeeOne || this.employeeTwo == ((EmployeeKey) o).employeeTwo;
        return false;
    }

    @Override
    public int hashCode() {
        return (this.employeeOne << 16) + this.employeeTwo;
    }

    @Override
    public String toString() {
        return "Employees: " + employeeOne + ", " + employeeTwo;
    }
}