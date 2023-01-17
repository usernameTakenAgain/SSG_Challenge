import java.sql.*;

public class MySQLDB {

    ResultSet resultSet;
    Statement statement;
    Connection connection = null;



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
} // class ends
