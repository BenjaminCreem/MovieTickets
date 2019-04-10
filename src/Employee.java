import java.time.LocalDate;

public class Employee extends User{

    String email;
    boolean isManager;

    @Override
    public String name() {
        return "";
    }

    //Email is used just for sign in purposes, and if this didn't return empty
    //then on the seat selection screen the employee password would appear
    @Override
    public String email() {
        return "";
    }

    @Override
    public String creditNum() {
        return "";
    }

    @Override
    public LocalDate creditExpDate() {
        return null;
    }

    @Override
    public String secCode() {
        return "";
    }

    @Override
    public String zipCode() {
        return "";
    }

    public Boolean isManager(){
        return isManager;
    }
}
