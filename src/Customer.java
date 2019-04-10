import java.time.LocalDate;

public class Customer extends User{
    String name;
    String email;
    String creditNum;
    LocalDate creditExpDate;
    String secCode;
    String zipCode;

    public String name(){
        return name;
    }

    public String toString(){
        return "Email: " + email + ", CreditNum: " + creditNum + ", CreditExpDate: " + creditExpDate + ", Security Code: " + secCode + ", Zip Code: " + zipCode;
    }

    public String email(){
        return email;
    }

    public String creditNum(){
        return creditNum;
    }

    public LocalDate creditExpDate(){
        return creditExpDate;
    }

    public String secCode(){
        return secCode;
    }

    public String zipCode(){
        return zipCode;
    }
}

