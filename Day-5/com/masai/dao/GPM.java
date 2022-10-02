package com.masai.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

public interface GPM {
    public void gpmOption() throws SQLException, IOException;
    public void gpmLogin() throws SQLException;
    public void createEmployee(BufferedReader bufferedReader) throws SQLException, IOException;
    public void updateEmployee(BufferedReader bufferedReader) throws SQLException;
    public void deleteEmployee(BufferedReader bufferedReader) throws SQLException;
    public void issueJobCard(BufferedReader bufferedReader) throws SQLException, IOException;
    public void projectAllotmentEmployee(BufferedReader bufferedReader) throws SQLException, IOException;

}