import java.sql.*;

public class AccountManager {

    private Connection databaseConn;

    public AccountManager(){
        String driverName = "com.mysql.cj.jdbc.Driver";
        String serverName = "localhost";
        String myDatabase = "MovieTickets";
        String url = "jdbc:mysql://" + serverName + "/" + myDatabase;
        String username = "root";
        String password = "shockfire3DG";
        try {
            Class.forName(driverName);
            databaseConn = DriverManager.getConnection(url, username, password);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Take in an employee's information and create an account by adding that information
     * to the employee table in the database.
     * @return true if account was created successfully, false if there was an error
     */
    public boolean createEmployeeAccount(String email, String password, String confirmPassword, boolean isManager){
        //Check password and confirm password match
        if(password.equals(confirmPassword)){
            try {
                //Make sure no account already exists with that username
                Statement stmt = databaseConn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM employees WHERE email = '" + email + "'");
                byte realIsManager;
                if(isManager)
                    realIsManager = 1;
                else
                    realIsManager = 0;
                if(!rs.next()){
                    stmt.executeUpdate("INSERT INTO employees (email, password, isManager) VALUES ('" + email + "', '" + password + "', '" + realIsManager + "');");
                    return true;
                }
            }
            catch(java.sql.SQLException e){
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Passwords do not match!"); //Debug stuff
        }
        return false;
    }

    /**
     * Take in an customer's information and create an account by adding that information
     * to the customer table in the database.
     * @return true if account was created successfully, false if there was an error
     */
    public boolean createCustomerAccount(String name, String email, String password, String confirmPassword, String creditNum, Date expDate, String secCode, String zip){
        if(password.equals(confirmPassword)){
            try {
                //Make sure no account already exists with that username
                Statement stmt = databaseConn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT email FROM customers WHERE email = '" + email + "'");
                if(!rs.next())
                {
                    stmt.executeUpdate("INSERT INTO customers (name, email, password, creditNum, creditExpDate, secCode, zipCode) VALUES ('" + name + email + "', '" + password + "', '" + creditNum + "', '" +
                            expDate + "', '" + secCode + "', '" + zip + "');");
                    return true;
                }
                else
                {
                    System.out.println("Account already exists!");
                }
            }
            catch(java.sql.SQLException e){
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Passwords do not match!"); //Debug stuff
        }
        return false;
    }


    /**
     * Gets a customer class from an email address. That customer class will contain
     * the customer's saved payment info.
     * @param email - email for customer identifier
     * @return - returns a customer based on the info that the customer provided
     */
    public Customer getPaymentInfo(String email){
        Customer c = new Customer();
        try {
            Statement stmt = databaseConn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name, email, creditNum, creditExpDate, secCode, zipCode FROM customers WHERE email = '" + email + "'");
            while(rs.next()){ //Should only be one person returned so this should only go through once
                c.name = rs.getString(1);
                c.email = rs.getString(2);
                c.creditNum = rs.getString(3);
                c.creditExpDate = rs.getDate(4).toLocalDate();
                c.secCode = rs.getString(5);
                c.zipCode = rs.getString(6);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //Debug
        System.out.println(c);
        return c;
    }

    public Employee getEmployee(String email){
        Employee emp = new Employee();
        try{
            Statement stmt = databaseConn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT email, isManager FROM employees WHERE email = '" + email + "'");
            while(rs.next()){
                emp.email = rs.getString(1);
                byte m = rs.getByte(2);
                Boolean isManager;
                isManager = m == 1; //Convert Byte to Boolean
                emp.isManager = isManager;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return emp;
    }

    /**
     * Verify's that a customer has entered the correct username and password
     * @param email - username
     * @param password -password
     * @return - true if username and password match, false otherwise
     */
    public boolean customerSignin(String email, String password){
        try{
            Statement stmt = databaseConn.createStatement();
            String query = "SELECT * FROM customers WHERE email = '" + email + "' AND password = '" + password + "'";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Verify's that an employee has entered the correct username and password
     * @param email - username
     * @param password -password
     * @return - true if username and password match, false otherwise
     */
    public boolean employeeSignin(String email, String password){
        try{
            Statement stmt = databaseConn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT email, password FROM employees WHERE email = '" + email + "' AND password = '" + password + "'");
            if(rs.next()){
                return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
