package com.masai.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public interface BDO {

    public void viewComplaints();
    public void createGpm(BufferedReader bufferedReader) throws SQLException;
    public void updateGpm(BufferedReader bufferedReader) throws SQLException;
    public void bdoLogin(BufferedReader bufferedReader) throws SQLException;
    public boolean bdoOption() throws SQLException, ParseException, IOException;
    public void deleteGpm(BufferedReader bufferedReader) throws SQLException;
    public void createProject(BufferedReader bufferedReader) throws SQLException, ParseException;
    public void updateProject(BufferedReader bufferedReader) throws SQLException, IOException;
    public void deleteProject(BufferedReader bufferedReader) throws SQLException, IOException;
}