import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Showing {

    int id;
    String movieName;
    String showtime;
    String seats;
    int theaterNumber;
    Connection db;
    TheaterManager tm;

    public Showing(int myid, String name, String show, String s, int n, TheaterManager thetManag){
        id = myid;
        movieName = name;
        showtime = show;
        seats = s;
        theaterNumber = n;
        tm = thetManag;
        db = tm.getDbConn();
    }

    public Showing(int myid, TheaterManager thetManag){
        id = myid;
        tm = thetManag;
        db = tm.getDbConn();
        try {
            Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM showings WHERE id = '" + id + "'");
            if(rs.next()) {
                movieName = rs.getString(2);
                showtime = rs.getString(3);
                seats = rs.getString(4);
                theaterNumber = rs.getInt(5);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void reserveSeat(int n){
        StringBuilder newSeats = new StringBuilder(seats);
        newSeats.setCharAt(n, 'o'); //Set to occupied
        seats = newSeats.toString();
        //Update database
        try {
            Statement stmt = db.createStatement();
            String sql = "UPDATE showings SET seats = '" + seats + "' WHERE id = '" + id + "'";
            stmt.executeUpdate(sql);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
