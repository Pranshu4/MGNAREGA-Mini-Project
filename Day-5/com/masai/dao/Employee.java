package com.masai.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

public interface Employee {
    public boolean employeeOption() throws SQLException, IOException;
    public void employeeLogin(BufferedReader bufferedReader) throws SQLException;
    public void employeeDetails() throws SQLException;
    // public void fileComplain(BufferedReader bufferedReader) throws SQLException, IOException;
}