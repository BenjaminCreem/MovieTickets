import java.util.Date;

public class Customer {
    String email;
    String creditNum;
    Date creditExpDate;
    String secCode;
    String zipCode;

    public String toString(){
        return "Email: " + email + ", CreditNum: " + creditNum + ", CreditExpDate: " + creditExpDate + ", Security Code: " + secCode + ", Zip Code: " + zipCode;
    }
}

