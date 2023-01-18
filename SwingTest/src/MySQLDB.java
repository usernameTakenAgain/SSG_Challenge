import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class MySQLDB {

    ResultSet resultSet;
    Statement statement;
    Connection connection = null;
    PreparedStatement PreparedStatement;


    public MySQLDB(){
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/walkiedb",
                    "root", "arj@nK5398");


            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "select * from persoon;");
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void UpdateUserRow(String naam, String email, int gewicht, int leeftijd){
        try {
            statement = connection.createStatement();
            statement.executeUpdate("update persoon SET Naam = '"+naam+"'," +
                    "Leeftijd = '"+ leeftijd +"', Gewicht = '"+ gewicht + "', " +
                    " Email = '"+email+"' where PersoonId = 1; ");
        } catch (Exception e){System.out.println(e);}
    }
    public void closeConnection(){
        try {
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {System.out.println(e);}
    }

    public void CreateLoopGedrag(int Stappen, int KoffieGehaald){
        try{
            statement = connection.createStatement();
            String query = "insert into gewoontes (Datum, Stappen, KoffieGehaald) values(NOW(),"+Stappen+",  "+ KoffieGehaald+")";
            int antwoord = statement.executeUpdate(query);
            System.out.println(antwoord);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void GetLoopGedrag(){
        try{
            statement = connection.createStatement();
            String query = "select * from gewoontes where datum between date_sub(now(),INTERVAL 1 WEEK) and now() order by datum desc limit 5 ;";
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            System.out.println(e);
        }
    }
} // class ends
