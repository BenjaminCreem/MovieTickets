import java.time.LocalDate;

public abstract class User {
    public abstract String name();
    public abstract String email();
    public abstract String creditNum();
    public abstract LocalDate creditExpDate();
    public abstract String secCode();
    public abstract String zipCode();
}
