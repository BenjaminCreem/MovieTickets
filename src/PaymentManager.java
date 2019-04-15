import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentManager {

    private Connection databaseConn;

    //ticket, popcorn, drink, candy prices
    private double tprice = 13.69;
    private double pprice = 7.10;
    private double dprice = 5.59;
    private double cprice = 3.99;

    public PaymentManager(){
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

    //validates if the card is 16 digits
    public boolean validateCard(String ccnum) {
        if (ccnum.length() == 16) {
            return true;
        }
        return false;
    }

    //indicates whether a ticket, popcorn, drink, or candy is purchased
    public void updateRevenue(boolean tick, boolean pop, boolean dr, boolean can){
        try {
            //check if there's an entry for current date
            Statement stmt = databaseConn.createStatement();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();

            ResultSet rs = stmt.executeQuery("SELECT * FROM revenue WHERE date = '" + currentDate + "'");
            if(!rs.next()) {
                stmt.executeUpdate("INSERT INTO revenue (date, total, ticket, popcorn, drink, candy) VALUES ('" + currentDate + 0 + "', '" + 0 + "', '" + 0 + "', '" +
                        0 + "', '" + 0 + "', '" + 0 + "');");
            }
            //updates total and whatever else was marked true with the correct pricing
            else{
                String sql = "SELECT total FROM revenue WHERE date = '" + currentDate + "'";
                ResultSet total = stmt.executeQuery(sql);
                //if ticket is purchased
                if (tick){

                    sql = "UPDATE revenue SET total = '" + total+tprice + "' WHERE date = '" + currentDate + "'";
                    stmt.executeUpdate(sql);

                    sql = "SELECT ticket FROM revenue WHERE date = '" + currentDate + "'";
                    ResultSet ticket = stmt.executeQuery(sql);
                    sql = "UPDATE revenue SET ticket = '" + ticket+tprice + "' WHERE date = '" + currentDate + "'";
                    stmt.executeUpdate(sql);
                }
                //if popcorn is purchased
                if(pop){
                    sql = "UPDATE revenue SET total = '" + total+pprice + "' WHERE date = '" + currentDate + "'";
                    stmt.executeUpdate(sql);

                    sql = "SELECT popcorn FROM revenue WHERE date = '" + currentDate + "'";
                    ResultSet popcorn = stmt.executeQuery(sql);
                    sql = "UPDATE revenue SET popcorn = '" + popcorn+pprice + "' WHERE date = '" + currentDate + "'";
                    stmt.executeUpdate(sql);
                }
                //if drink is purchased
                if(dr){
                    sql = "UPDATE revenue SET total = '" + total+dprice + "' WHERE date = '" + currentDate + "'";
                    stmt.executeUpdate(sql);

                    sql = "SELECT drink FROM revenue WHERE date = '" + currentDate + "'";
                    ResultSet drink = stmt.executeQuery(sql);
                    sql = "UPDATE revenue SET drink = '" + drink+dprice + "' WHERE date = '" + currentDate + "'";
                    stmt.executeUpdate(sql);
                }
                //if candy is purchased
                if(can){
                    sql = "UPDATE revenue SET total = '" + total+cprice + "' WHERE date = '" + currentDate + "'";
                    stmt.executeUpdate(sql);

                    sql = "SELECT candy FROM revenue WHERE date = '" + currentDate + "'";
                    ResultSet candy = stmt.executeQuery(sql);
                    sql = "UPDATE revenue SET candy = '" + candy+cprice + "' WHERE date = '" + currentDate + "'";
                    stmt.executeUpdate(sql);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
