package com.masai.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.masai.utility.databaseConnection;
import com.masai.valid.Validator;

public class EmployeeImpl implements Employee {
    databaseConnection db = new databaseConnection();
    Validator vl = new Validator();
    Scanner sc = new Scanner(System.in);
    int UID;

    public boolean employeeOption() throws SQLException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Choose your option \n 1.See your Profile \n 2.file your complain \n 3.Exit \n");
        int i=sc.nextInt();
       while(i<=3) {
           if (i == 1)
               employeeDetails();
           else if (i == 2)
               fileComplain(bufferedReader);
           else if (i == 3) {
               System.out.println("\t****Thanks for using 'Mahatma Gandhi National Rural Employment Act' system**** ");
               break;
           }
           System.out.print("Choose your option \n 1.See your Profile \n 2.file your complain \n 3.Exit \n");
           i = sc.nextInt();
           return true;
       }
       return true;
    }
    public void employeeLogin(BufferedReader bufferedReader) throws SQLException {
        try {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            System.out.print("Enter your EmailId: ");
            String Email = bufferedReader.readLine();
            System.out.print("Enter your Password: ");
            String Password = bufferedReader.readLine();
            if (vl.isValid(Email)) {
                ResultSet rs = stmt.executeQuery("select * from employee where email = '" + Email + "'  AND password = '" + Password + "' ");
                if (rs.next()) {
                    int mId = rs.getInt(1);
                    this.UID = mId;
                    con.close();
                    stmt.close();
                    System.out.print("****** WELCOME TO Employee MANAGEMENT ******");
                    employeeOption();
                } else
                    System.out.print("EmailId and Password is incorrect");
            } else
                System.out.print("Email is not valid");
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public void employeeDetails() throws SQLException{
        try {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            int mid = this.UID;
            ResultSet rs = stmt.executeQuery("select employee.name,employee.age,employee." +
                    "gender,employee.address,employee." +
                    "pin,employeeWorks.wageComputation,employeeWorks" +
                    ".numberOfDays,employeeWorks.projectName FROM employee INNER JOIN " +
                    " employeeWorks ON employee.email=employeeWorks.memail" +
                    " where mId = '" + mid + "'");
            if (rs.next()) {
                System.out.println("Name: " + rs.getString(1));
                System.out.println("Age: "+rs.getInt(2));
                System.out.println("Gender: "+rs.getString(3));
                System.out.println("Address: "+rs.getString(4));
                System.out.println("Pincode: "+rs.getInt(5));
                System.out.println("Wage Computation: "+rs.getInt(6));
                System.out.println("Number of Days: "+rs.getInt(7));
                System.out.println("Project Name = "+rs.getString(8));
                System.out.println("\n---------------------------------");
            }
            else
                System.out.println("No detail is present!!!");
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void fileComplain(BufferedReader bufferedReader) throws SQLException, IOException {
        try {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            System.out.println("File your Complain here !!!...");
            int mid = this.UID;
            System.out.println("Write your Issues: ");
            String issue = bufferedReader.readLine();
            stmt.executeUpdate("insert into report(mId,issue,created_at)values('" + mid + "','" + issue + "',CURRENT_TIMESTAMP)");
            System.out.println("Complaint filed!!!");
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
