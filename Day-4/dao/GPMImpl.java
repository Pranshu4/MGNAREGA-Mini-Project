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

public class GPMImpl implements GPM{
    databaseConnection db = new databaseConnection();
    Validator vl = new Validator();
    Scanner sc = new Scanner(System.in);
    int UID;
    public void gpmOption() throws SQLException, IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Choose your option \n 1.Create Employee \n 2.Update Employee \n 3.Delete Employee" +
                    " \n 4. Project Allotment \n 5. Issue Job Card \n 6. Exit \n");
            int i = sc.nextInt();
            while (i <= 6) {
                if (i == 1)
                    createEmployee(bufferedReader);
                else if (i == 2)
                    updateEmployee(bufferedReader);
                else if (i == 3)
                    deleteEmployee(bufferedReader);
                else if (i == 4)
                    projectAllotmentEmployee(bufferedReader);
                else if (i == 5)
                    issueJobCard(bufferedReader);
                else if(i == 6) {
                    System.out.println("\t****Thanks for using 'Mahatma Gandhi National Rural Employment Act' system**** ");
                    break;
                }
                System.out.print("Choose your option \n 1.Create Employee \n 2.Update Employee \n 3.Delete Employee" +
                        " \n 4. Project Allotment \n 5. Issue Job Card \n 6. Exit \n");
                i = sc.nextInt();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void gpmLogin() throws SQLException {
        try {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            System.out.print("Enter your EmailId: ");
            String Email = sc.nextLine();
            System.out.print("Enter your Password: ");
            String Password = sc.nextLine();
            if (vl.isValid(Email)) {
                ResultSet rs = stmt.executeQuery("select * from gpm where email = '" + Email + "'  AND password = '" + Password + "' ");
                if (rs.next()) {
                    int gpmId = rs.getInt(1);
                    this.UID = gpmId;
                    con.close();
                    stmt.close();
                    System.out.print("****** WELCOME TO GPM MANAGEMENT ******");
                    gpmOption();
                } else
                    System.out.print("EmailId and Password is incorrect");
            } else
                System.out.print("Email is not valid");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void createEmployee(BufferedReader bufferedReader) throws SQLException, IOException {
        try {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            int gpmId = this.UID;
            System.out.println("Create new employee:");
            System.out.println("Name: ");
            String name = bufferedReader.readLine();
            System.out.println("Age:  ");
            int age = bufferedReader.read();
            System.out.println("Gender(M/F)");
            String gender = bufferedReader.readLine();
            System.out.println("Address");
            String address = bufferedReader.readLine();
            System.out.println("Pincode: ");
            int pincode = bufferedReader.read();
            char is_deleted = 'F';
            System.out.println("Email: ");
            String email = bufferedReader.readLine();
            if (vl.isValid(email)) {
                System.out.println("Password: ");
                String password = bufferedReader.readLine();
                stmt.executeUpdate("Insert into employee(email,name,age,gender,address,pin,gId,password," +
                        "created_at,is_deleted)values('" + email + "','" + name + "','" + age + "','" + gender + "','" + address + "','" + pincode + "','" + gpmId + "','"+password+"',CURRENT_TIMESTAMP,'" + is_deleted + "')");
                System.out.println("Employee is Created!!");
            } else
                System.out.println("Email is Invalid!!!");
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void updateEmployee(BufferedReader bufferedReader) throws SQLException{
        try {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            vl.listOfEmployee(stmt);
            System.out.print("Enter EmailId of Employee: ");
            String email = bufferedReader.readLine();
            if (vl.isValid(email)) {
                ResultSet rs = stmt.executeQuery("select * from employee where email = '" + email + "' ");
                if (rs.next()) {
                    System.out.print("1.Name: " + rs.getString(3));
                    System.out.print("\n2.Age: " + rs.getInt(4));
                    System.out.println("\nGender: "+ rs.getString(4));
                    System.out.print("\n3.Address: " + rs.getString(6));
                    System.out.print("\n4.Pincode: " + rs.getInt(7));
                    System.out.print("\n5.Password:" + rs.getString(9));
                    System.out.print("\n You can only Update\n 1. Name 2.Address 3.Pincode 4.Password \n Which feild You want to Update");
                    int ch = bufferedReader.read();
                    if (ch == 1) {
                        System.out.print("Please update your Name: ");
                        String name = bufferedReader.readLine();
                        stmt.executeUpdate("Update employee set name ='" + name + "' ,updated_at=CURRENT_TIMESTAMP where email = '" + email + "'");
                        System.out.println("Updated!!!");
                    }else if (ch == 2) {
                        System.out.print("Please update your Address: ");
                        String address = bufferedReader.readLine();
                        stmt.executeUpdate("Update employee set address ='" + address + "' ,updated_at=CURRENT_TIMESTAMP where email = '" + email + "'");
                        System.out.println("Updated!!!");
                    }else if (ch == 3) {
                        System.out.print("Update your Pincode: ");
                        int pincode = bufferedReader.read();
                        stmt.executeUpdate("Update employee set pin ='" + pincode + "' ,updated_at=CURRENT_TIMESTAMP where email = '" + email + "'");
                        System.out.println("Updated!!!");
                    }else if (ch == 4) {
                        System.out.print("Update your Password:");
                        String password = bufferedReader.readLine();
                        stmt.executeUpdate("Update employee set password ='" + password + "' ,updated_at=CURRENT_TIMESTAMP where email = '" + email + "'");
                        System.out.println("Updated!!!");
                    }else if (ch > 4)
                        System.out.print("Invalid Choice");
                } else
                    System.out.print("this email is not present");
            } else
                System.out.println("email is invalid!");
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public void deleteEmployee(BufferedReader bufferedReader) throws SQLException{
        try {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            vl.listOfEmployee(stmt);
            System.out.print("Enter EmailId to delete the user: ");
            String email = bufferedReader.readLine();
            if (vl.isValid(email)) {
                stmt.executeUpdate("update employee set is_deleted = 'T' where email = '"+email+"'");
                System.out.print("User is Deleted!!!");
            } else
                System.out.print("Email is Invalid!!");
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void issueJobCard(BufferedReader bufferedReader) throws SQLException, IOException {
        try {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            System.out.println("Issue Admit card..");
            System.out.println("Enter employee's Email: ");
            String email = bufferedReader.readLine();
            if (vl.isValid(email)) {
                ResultSet rs = stmt.executeQuery("select * from employee where email = '" + email + "'");
                if (rs.next()) {
                    System.out.println("Name: " + rs.getString(3));
                    System.out.println("Email: " + rs.getString(2));
                    System.out.println("Age: " + rs.getInt(4));
                    System.out.println("Gender: " + rs.getString(5));
                    System.out.println("Address: " + rs.getString(6));
                    System.out.println("pincode: " + rs.getInt(7));
                    System.out.println("Successfully Admit Card is generated!...");
                } else
                    System.out.println("EmailId is not registered!!!");
            } else
                System.out.println("Invalid Email!");
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void projectAllotmentEmployee(BufferedReader bufferedReader) throws SQLException, IOException {
        try {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            System.out.println("Allotment of Project to Employee!!!... ");
            vl.listOfEmployee(stmt);
            System.out.println("Enter Email:");
            String email = bufferedReader.readLine();
            if (vl.isValid(email)) {
                ResultSet rs = stmt.executeQuery("select * from employee where email= '" + email + "'");
                if (rs.next()) {
                    System.out.println("NumberOfDays: ");
                    int noOfDays = bufferedReader.read();
                    int wage = noOfDays * 100;
                    System.out.println("Project Name:");
                    String projectName = bufferedReader.readLine();
                    String wageStatus = "Not Active";
                    String projectStatus = "Not Active";
                    stmt.executeUpdate("insert into employeeWorks(projectName,memail,numberOfDays,wageComputation,wageStatus,ProjectStatus)values('" + projectName + "','" + email + "','" + noOfDays + "','" + wage + "','" + wageStatus + "','" + projectStatus + "')");
                    System.out.println("Project is Allotted!!");
                } else
                    System.out.println("Project is already allotted to this Employee!!");
            } else
                System.out.println("Email is Invalid!!!");
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}