import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TheaterManager {

    private Connection databaseConn;

    public TheaterManager(){
        String driverName = "com.mysql.cj.jdbc.Driver";
        String serverName = "localhost";
        String myDatabase = "MovieTickets";
        String url = "jdbc:mysql://" + serverName + "/" + myDatabase;
        String username = "root";
        String password = "KFZ73bx844FB10xH";
        try {
            Class.forName(driverName);
            databaseConn = DriverManager.getConnection(url, username, password);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public Connection getDbConn(){
        return databaseConn;
    }

    public ArrayList<Showing> getShowings(int theaterNumber){
        ArrayList<Showing> showings = new ArrayList<Showing>();
        try {
            Showing s;
            Statement stmt = databaseConn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM showings WHERE theaterNumber = '" + theaterNumber + "'");
            while(rs.next()) {
                s = new Showing(rs.getInt(1));
                showings.add(s);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return showings;
    }
}
