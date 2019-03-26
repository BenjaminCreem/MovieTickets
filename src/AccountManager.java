import java.sql.*;

public class AccountManager {

    Connection databaseConn;
    String databasePath = "jbdc:mysql://localhost:3306/movietickets";

    public AccountManager(){
        try {
            databaseConn = DriverManager.getConnection(databasePath);
        }
        catch(java.sql.SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Take in an employee's information and create an account by adding that information
     * to the employee table in the database.
     * @return true if account was created successfully, false if there was an error
     */
    public boolean createEmployeeAccount(String email, String password, String confirmPassword, boolean isManager){
        if(password.equals(confirmPassword)){
            return true;
        }
        return false;
    }

    /**
     * Take in an customer's information and create an account by adding that information
     * to the customer table in the database.
     * @return true if account was created successfully, false if there was an error
     */
    public boolean createCustomerAccount(String email, String password, String confirmPassword, String creditNum, Date expDate, String secCode, String zip){
        if(password.equals(confirmPassword)){
            return true;
        }
        return false;
    }


    /**
     * Gets a custmer class from an email address. That customer class will contain
     * the customer's saved payment info.
     * @param email
     * @return
     */
    public Customer getPaymentInfo(String email){
        Customer c = new Customer();
        return c;
    }

    /**
     * Verify's that a customer has entered the correct username and password
     * @param email - username
     * @param password -password
     * @return - true if username and password match, false otherwise
     */
    public boolean customerSignin(String email, String password){
        return true;
    }

    /**
     * Verify's that an employee has entered the correct username and password
     * @param email - username
     * @param password -password
     * @return - true if username and password match, false otherwise
     */
    public boolean employeeSignin(String email, String password){
        return true;
    }
}
