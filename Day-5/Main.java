
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import com.masai.dao.Employee;
import com.masai.dao.BDO;
import com.masai.dao.BDOImpl;
import com.masai.dao.EmployeeImpl;
import com.masai.dao.GPM;
import com.masai.dao.GPMImpl;

public class Main {

    public static void main(String[] args) throws SQLException, ParseException {
	
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BDO bdo = new BDOImpl();
            GPM gpm = new GPMImpl();
            Employee employee = new EmployeeImpl();
            Scanner sc = new Scanner(System.in);
            System.out.print("Choose your option to Login as Different User Pofile\n 1. BDO \n 2. GPM \n 3. EMPLOYEE\n 4. Exit \n");
            int i = sc.nextInt();
            while (i <= 4) {
                if (i == 1)
                    bdo.bdoLogin(bufferedReader);
                else if (i == 2)
                    gpm.gpmLogin();
                else if (i == 3)
                    employee.employeeLogin(bufferedReader);
                else if (i ==4){
                    System.out.println("Thank you!!!");
                    break;
                }
                System.out.print("Choose your option to Login as Different User Profile\n 1. BDO \n 2. GPM \n 3. EMPLOYEE\n 4. Exit \n");
                i = sc.nextInt();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
