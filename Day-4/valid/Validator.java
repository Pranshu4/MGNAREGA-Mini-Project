package com.masai.valid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import com.masai.utility.databaseConnection;

public class Validator {
    databaseConnection db = new databaseConnection();
    Connection con = db.getConnection();
    public boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public boolean listOfProjects(Statement stmt) throws SQLException {
        try {
            ResultSet rs = stmt.executeQuery("select * from project where is_deleted = 'F'");
            while (rs.next())
                System.out.println("Project Name: " + rs.getString(2)+ "\n "+"Address: " + rs.getString(3)+"\n "+"TotalEmployees: " + rs.getInt(4)+"\n "+"Cost Estimation: " + rs.getFloat(5)+"\n "+"Start Date: " + rs.getDate(6)+"\n "+"End Date: " + rs.getDate(7)+"\n "+"Project Type: "+rs.getString(8)+"\n---------------");
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        }
    }

    public boolean listOfGpm(Statement stmt) throws SQLException {
        try {
            ResultSet rs = stmt.executeQuery("select * from gpm where is_deleted= 'F'");
            while (rs.next())
                System.out.println("Email: " + rs.getString(2)+ "\n "+"Name: " + rs.getString(3)+"\n "+"Age: " + rs.getInt(4)+"\n "+"Address: " + rs.getString(5)+"\n "+"Pincode: " + rs.getInt(7)+"\n "+"Password: " + rs.getString(8)+"\n---------------");
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        }
    }
    public boolean listOfEmployee(Statement stmt) throws SQLException{
        try {
            System.out.println("Here is all the list of Employee.....");
            ResultSet rs = stmt.executeQuery("select * from employee where is_deleted= 'F'");
            while (rs.next())
                System.out.println("Email: " + rs.getString(2)+ "\n "+"Name: " + rs.getString(3)+"\n "+"Age: " + rs.getInt(4)+"\n "+"Address: " + rs.getString(6)+"\n "+"Pincode: " + rs.getInt(7)+"\n "+"Password: " + rs.getString(9)+"\n---------------");
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        }

    }

    public void showEmployee() throws SQLException, IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            Statement stmt = con.createStatement();
            System.out.println("Enter Gpm EmailId: ");
            String email = bufferedReader.readLine();
            if (isValid(email)) {
                ResultSet rs = stmt.executeQuery("select employee.name,employee.age,employee.gender,employee.address," +
                        "employee.pin,employeeWorks.wageComputation,employeeWorks.numberOfDays FROM employee INNER JOIN " +
                        " employeeWorks ON employee.email=employeeWorks.memail  INNER JOIN gpm ON gpm.gId = employee.gId " +
                        "where gpm.email='" + email + "'");
                System.out.println("Hers is the employees under this GPM");
                while (rs.next()) {
                    System.out.println("Employee Name: " + rs.getString(1) +" \n"+ "Age: " +
                            rs.getInt(2)+" \n"+"Gender: "+rs.getString(4)+" \n"+"Pincode: "
                            +rs.getInt(5)+" \n"+"Wage Computation: "+rs.getInt(6)+" \n"+"NUmber Of Days: "+rs.getInt(7)+"\n---------------");
                }
            }
            else
                System.out.println("Invalid Email!!!...");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
