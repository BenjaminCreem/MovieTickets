import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

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

    public GridPane getPaymentData(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5.0);
        grid.setHgap(5.0);
        Label dateLbl = new Label("Date");
        GridPane.setConstraints(dateLbl, 0, 0);
        Label totalLbl = new Label("Total");
        GridPane.setConstraints(totalLbl, 1, 0);
        Label ticketLbl = new Label("Ticket");
        GridPane.setConstraints(ticketLbl, 2, 0);
        Label popLbl = new Label("Popcorn");
        GridPane.setConstraints(popLbl, 3, 0);
        Label drinkLbl = new Label("Drinks");
        GridPane.setConstraints(drinkLbl, 4, 0);
        Label candyLbl = new Label("Candy");
        GridPane.setConstraints(candyLbl, 5, 0);
        grid.getChildren().addAll(dateLbl, totalLbl, ticketLbl, popLbl, drinkLbl, candyLbl);

        try {
            Statement stmt = databaseConn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM revenue");
            int i = 1;
            while(rs.next()){
                Date date = rs.getDate(1);
                double total = rs.getDouble(2);
                double ticket = rs.getDouble(3);
                double popcorn = rs.getDouble(4);
                double drink = rs.getDouble(5);
                double candy = rs.getDouble(6);
                Label dat = new Label("" + date);
                Label tot = new Label("" + total);
                Label tick = new Label("" + ticket);
                Label pop = new Label("" + popcorn);
                Label drnk = new Label("" + drink);
                Label cndy = new Label("" + candy);
                GridPane.setConstraints(dat, 0, i);
                GridPane.setConstraints(tot, 1, i);
                GridPane.setConstraints(tick, 2, i);
                GridPane.setConstraints(pop, 3, i);
                GridPane.setConstraints(drnk, 4, i);
                GridPane.setConstraints(cndy, 5, i);
                grid.getChildren().addAll(dat, tot, tick, pop, drnk, cndy);
                i++;
            }

        }catch(Exception ignored){

        }

        return grid;
    }

    //indicates whether a ticket, popcorn, drink, or candy is purchased
    public void updateRevenue(boolean tick, boolean pop, boolean dr, boolean can){
        try {
            //check if there's an entry for current date
            Statement stmt = databaseConn.createStatement();

            LocalDate ld = LocalDate.now();
            Date currentDate = java.sql.Date.valueOf(ld.toString());

            ResultSet rs = stmt.executeQuery("SELECT * FROM revenue WHERE date = '" + currentDate + "'");
            if(!rs.next()) {
                stmt.executeUpdate("INSERT INTO revenue (date, total, ticket, popcorn, drink, candy) VALUES ('" + currentDate + "', '" + 0 + "', '" + 0 + "', '" +
                        0 + "', '" + 0 + "', '" + 0 + "');");
            }
            //updates total and whatever else was marked true with the correct pricing
            String sql = "SELECT total FROM revenue WHERE date = '" + currentDate + "'";
            ResultSet totalResult = stmt.executeQuery(sql);
            double total = 0.0;
            if(totalResult.next()) {
                total = totalResult.getDouble(1);
            }
            DecimalFormat decForm = new DecimalFormat("###.##");

            //if ticket is purchased
            if (tick){


                sql = "UPDATE revenue SET total = '" + decForm.format(total+tprice) + "' WHERE date = '" + currentDate + "'";
                stmt.executeUpdate(sql);

                sql = "SELECT ticket FROM revenue WHERE date = '" + currentDate + "'";
                ResultSet ticketResult = stmt.executeQuery(sql);
                if(ticketResult.next()) {
                    double ticket = ticketResult.getDouble(1);
                    sql = "UPDATE revenue SET ticket = '" + decForm.format(ticket + tprice) + "' WHERE date = '" + currentDate + "'";
                    stmt.executeUpdate(sql);
                }
            }
            //if popcorn is purchased
            if(pop){
                sql = "UPDATE revenue SET total = '" + decForm.format(total+pprice) + "' WHERE date = '" + currentDate + "'";
                stmt.executeUpdate(sql);

                sql = "SELECT popcorn FROM revenue WHERE date = '" + currentDate + "'";
                ResultSet popcornResult = stmt.executeQuery(sql);
                if(popcornResult.next()){
                    double popcorn = popcornResult.getDouble(1);
                    sql = "UPDATE revenue SET popcorn = '" + decForm.format(popcorn+pprice) + "' WHERE date = '" + currentDate + "'";
                    stmt.executeUpdate(sql);
                }

            }
            //if drink is purchased
            if(dr){
                sql = "UPDATE revenue SET total = '" + decForm.format(total+dprice) + "' WHERE date = '" + currentDate + "'";
                stmt.executeUpdate(sql);

                sql = "SELECT drink FROM revenue WHERE date = '" + currentDate + "'";
                ResultSet drinkResult = stmt.executeQuery(sql);
                if(drinkResult.next()) {
                    double drink = drinkResult.getDouble(1);
                    sql = "UPDATE revenue SET drink = '" + decForm.format(drink + dprice) + "' WHERE date = '" + currentDate + "'";
                    stmt.executeUpdate(sql);
                }
            }
            //if candy is purchased
            if(can){
                sql = "UPDATE revenue SET total = '" + decForm.format(total+cprice) + "' WHERE date = '" + currentDate + "'";
                stmt.executeUpdate(sql);

                sql = "SELECT candy FROM revenue WHERE date = '" + currentDate + "'";
                ResultSet candyResult = stmt.executeQuery(sql);
                if(candyResult.next()) {
                    double candy = candyResult.getDouble(1);
                    sql = "UPDATE revenue SET candy = '" + decForm.format(candy + cprice) + "' WHERE date = '" + currentDate + "'";
                    stmt.executeUpdate(sql);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
