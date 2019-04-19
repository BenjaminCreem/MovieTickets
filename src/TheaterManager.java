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
        String password = "shockfire3DG";
        try {
            Class.forName(driverName);
            databaseConn = DriverManager.getConnection(url, username, password);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void removeMovie(int id){
        try {
            Statement stmt = databaseConn.createStatement();
            stmt.executeUpdate("DELETE FROM showings WHERE id = " + id);
        }catch(Exception ignored){

        }
    }

    public boolean insertIntoMovies(String movieName, int theaterNumber, String showTime){
        if(showTime.length() == 4){
            try{
                Integer.parseInt(showTime);
                Statement stmt = databaseConn.createStatement();
                stmt.executeUpdate("INSERT INTO showings (movieName, showTime, theaterNumber) VALUES ('" + movieName + "', '" + showTime + "', '" + theaterNumber + "');");
            }catch(Exception e){
                return false;
            }
        }
        return true; //Only got here if we didnt return false
    }

    public Connection getDbConn(){
        return databaseConn;
    }

    public ArrayList<Showing> getShowings(String movieName){
        ArrayList<Showing> showings = new ArrayList<Showing>();
        try {
            Showing s;
            Statement stmt = databaseConn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM showings WHERE movieName = '" + movieName + "'");
            while(rs.next()) {
                s = new Showing(rs.getInt(1), this);
                showings.add(s);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return showings;
    }

    public ArrayList<String> getMovieNames(){
        ArrayList<String> movieNames = new ArrayList<>();
        try{
            Statement stmt = databaseConn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT movieName FROM showings GROUP BY movieName");
            while(rs.next()){
                movieNames.add(rs.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return movieNames;
    }
}
