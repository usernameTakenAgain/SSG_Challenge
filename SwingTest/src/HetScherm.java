import SwingTest.src.SerialClass;
import org.jfree.chart.ui.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fazecast.jSerialComm.*;

public class HetScherm {

    public static Gebruiker GebruikerGegevens = new Gebruiker();
    public  static Font fontKop = new Font("Arial Black", Font.BOLD, 20); // Hier wordt de font die we gebruiken opgeslagen
    public static Font fontVoet = new Font("Arial Black", Font.BOLD, 15); // Hier wordt de font die we gebruiken opgeslagen


    public static void createScherm() {


        JFrame hoofdScherm = new JFrame();//creating instance of JFrame

        JLabel Tekst = new JLabel("Gebruikers gegevens");
        Tekst.setBounds(230,0,300,40);
        Tekst.setBackground( new Color(118,181,197));
        Tekst.setFont(fontKop);

        JTextField gebruikers_naam = new JTextField();
        if(GebruikerGegevens.GetNaam() != ""){
            gebruikers_naam.setText(GebruikerGegevens.GetNaam());
        }
        gebruikers_naam.setBounds(250, 70, 200,50);

        JLabel GebruikersNaam = new JLabel("Voer uw naam in:");
        GebruikersNaam.setBounds(260,30,500,50);
        GebruikersNaam.setFont(fontVoet);
        GebruikersNaam.setBackground( new Color(118,181,197));


        JLabel Gebruikers_Email = new JLabel("Voer uw email in:");
        Gebruikers_Email.setBounds(260,120,500,50);
        Gebruikers_Email.setFont(fontVoet);
        Gebruikers_Email.setBackground( new Color(118,181,197));


        JTextField GebruikersEmail = new JTextField();
        if(GebruikerGegevens.GetEmail() != ""){
            GebruikersEmail.setText(GebruikerGegevens.GetEmail());
        }
        GebruikersEmail.setBounds(250, 160, 200,50);




        JLabel Gebruikers_Leeftijd = new JLabel("Voer uw leeftijd in:");
        Gebruikers_Leeftijd.setBounds(260,210,500,50);
        Gebruikers_Leeftijd.setFont(fontVoet);
        Gebruikers_Leeftijd.setBackground( new Color(118,181,197));


        JTextField GebruikersLeeftijd = new JTextField();
        GebruikersLeeftijd.setBounds(250, 250, 200,50);
        if(GebruikerGegevens.GetLeeftijd() != 0){
            GebruikersLeeftijd.setText(String.valueOf(GebruikerGegevens.GetLeeftijd()));
        }
        GebruikersLeeftijd.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
                    GebruikersLeeftijd.setEditable(true);
                }
                else{
                    GebruikersLeeftijd.setEditable(false);
                }
            }
        });

        JTextField GebruikersGewicht = new JTextField();
        GebruikersGewicht.setBounds(250, 330, 200,50);
        if(GebruikerGegevens.GetGewicht() != 0){
            GebruikersGewicht.setText(String.valueOf(GebruikerGegevens.GetGewicht()));
        }
        GebruikersGewicht.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
                    GebruikersGewicht.setEditable(true);
                }
                else{
                    GebruikersGewicht.setEditable(false);
                }
            }
        });


        JLabel Gebruikers_Gewicht = new JLabel("Voer uw gewicht in:");
        Gebruikers_Gewicht.setBounds(260,290,500,50);
        Gebruikers_Gewicht.setFont(fontVoet);
        Gebruikers_Gewicht.setBackground( new Color(118,181,197));

        JButton testButton = new JButton("Verzenden");//creating instance of JButton
        testButton.setBounds(290, 500, 100, 40);//x-axis, y-axis, width, height
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testKnopGedrukt(gebruikers_naam.getText(), GebruikersEmail.getText(), GebruikersLeeftijd.getText(), GebruikersGewicht.getText());
                hoofdScherm.setVisible(false);
            }
        });


        hoofdScherm.add(testButton);//Button wordt toegevoegd aan het frame.
        hoofdScherm.add(Tekst);
        hoofdScherm.add(gebruikers_naam);
        hoofdScherm.add(GebruikersNaam);
        hoofdScherm.add(GebruikersEmail);
        hoofdScherm.add(Gebruikers_Email);
        hoofdScherm.add(GebruikersLeeftijd);
        hoofdScherm.add(Gebruikers_Leeftijd);
        hoofdScherm.add(GebruikersGewicht);
        hoofdScherm.add(Gebruikers_Gewicht);

        hoofdScherm.getContentPane().setBackground( new Color(118,181,197));
        hoofdScherm.setSize(700, 700);//Afmetingen scherm.
        hoofdScherm.setLayout(null);//using no layout managers
        hoofdScherm.setVisible(true);//making the frame visible
        hoofdScherm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void testKnopGedrukt(String GebruikersnaamTekst, String GebruikersEmail, String GebruikersLeeftijd, String GebruikersGewicht){

        GebruikerGegevens= new Gebruiker(GebruikersnaamTekst,GebruikersEmail, Integer.parseInt(GebruikersLeeftijd), Integer.parseInt(GebruikersGewicht));
        HomePage(GebruikerGegevens);
    }

    public static void HomePage(Gebruiker MainUser){
        JFrame HomePage = new JFrame(); // Hier wordt het hoofdscherm gemaakt

        JLabel SchermTitel = new JLabel();
        SchermTitel.setText("HomePage");
        SchermTitel.setBounds(250,0,200,50);
        SchermTitel.setFont(fontKop);

        JLabel ingevuldeNaam = new JLabel();
        ingevuldeNaam.setText("Welkom, "+MainUser.GetNaam());
        ingevuldeNaam.setBounds(250, 50, 200,50);
        ingevuldeNaam.setFont(fontVoet);

        JButton verbindenKnop = new JButton("Verbinden");//creating instance of JButton
        verbindenKnop.setBounds(10, 600, 100, 40);//x-axis, y-axis, width, height
        verbindenKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hier wordt er verbinding gemaakt met de microbit");
                SerialClass SerialClass;
                SerialClass SerialClassInstance = new SerialClass();
                /*
                Alles Hierboven is setup om met de micro:bit te kunnen communiceren.
                resultaat is hoeveel seconden er uiteindelijk is gelopen
                Andere dingen die we kunnen meten zijn nog niet geimplementeerd
                */

                int resultaat;
                try {
                    resultaat = SerialClassInstance.leesGegevensPogingTwee();
                    System.out.println(resultaat);

                } catch (IOException e2) {
                    JOptionPane.showMessageDialog(null, "U mag maar een keer per dag uw resulaten uploaden!",
                            "InfoBox: " + "Upload fout", JOptionPane.INFORMATION_MESSAGE);
                }
                // TODO: hier het resultaat in de DB zetten
                LocalDateTime now = LocalDateTime.now();
                MySQLDB DBConnectie = new MySQLDB();
                DBConnectie.CreateLoopGedrag(1,1);
            }
        });

//        JButton AnalyseerGegevens = new JButton("Analyseer gegevens");
//        AnalyseerGegevens.setBounds(200, 600, 300, 40);
//        AnalyseerGegevens.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Hier word de gegevens van vandaag laten zien in tekst");
//            }
//        });

        JButton Details = new JButton("Details");
        Details.setBounds(580,600,100,40);
        Details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 Graph TestGraph = new Graph("Lopen");
                 TestGraph.createGraph(MainUser);
                 HomePage.setVisible(false);
                 TestGraph.setDefaultCloseOperation(TestGraph.HIDE_ON_CLOSE);
            }
        });

        String twoLines = "Persoons\ngegevens";
        JButton PersoonGegevensKnop = new JButton("<html>" + twoLines.replaceAll("\\n", "<br>") + "</html>");//creating instance of JButton
        PersoonGegevensKnop.setBounds(10, 10, 100, 100);//x-axis, y-axis, width, height
        PersoonGegevensKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage.setVisible(false);
                createScherm();
            }
        });

        HomePage.add(Details);
        HomePage.add(ingevuldeNaam);
        HomePage.add(SchermTitel);
        HomePage.add(verbindenKnop);
        HomePage.add(PersoonGegevensKnop);


        HomePage.getContentPane().setBackground( new Color(118,181,197));
        HomePage.setSize(700, 700);//Afmetingen scherm.
        HomePage.setLayout(null);//using no layout managers
        HomePage.setVisible(true);//making the frame visible
        HomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
