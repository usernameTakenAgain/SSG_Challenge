import java.sql.ResultSet;
import java.sql.SQLException;

public class Gebruiker {

    int leeftijd;
    int gewicht;
    String naam;
    String email;

    MySQLDB DBconnectie = new MySQLDB();
    ResultSet queryresultaat =DBconnectie.getResultSet();


    public Gebruiker() {

        try {
            queryresultaat.next();
            leeftijd = queryresultaat.getInt("Leeftijd");
            gewicht = queryresultaat.getInt("Gewicht");
            naam = queryresultaat.getString("Naam");
            email = queryresultaat.getString("Email");
        }catch (Exception e){System.out.println(e);}
    }
    public Gebruiker(String GebruikerNaam, String GebruikerMail, int GebruikerLeeftijd, int GebruikerGewicht){
        naam = GebruikerNaam;
        email = GebruikerMail;
        leeftijd = GebruikerLeeftijd;
        gewicht = GebruikerGewicht;

        DBconnectie.UpdateUserRow(this.naam, this.email, this.gewicht, this.leeftijd);

    }

    public void ConnectieSluite()
    {
        DBconnectie.closeConnection();
    }


    public String GetNaam(){
        return this.naam;
    }
    public String GetEmail(){
        return this.email;
    }
    public int GetGewicht(){
        return this.gewicht;
    }
    public int GetLeeftijd(){
        return this.leeftijd;
    }


}
