import java.time.LocalDate;

public class Employee extends User{
    @Override
    public String name() {
        return "";
    }

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
}
