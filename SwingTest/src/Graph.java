import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Graph extends ApplicationFrame {
    public Graph(String title) {
        super(title);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        MySQLDB SqlConnectie = new MySQLDB();
        SqlConnectie.GetLoopGedrag();
        ResultSet Result = SqlConnectie.getResultSet();
        try {
            int colomen = Result.getMetaData().getColumnCount();
            List<String[]> tabel = new ArrayList<>();
            while (Result.next()){
                String[] rij = new String[colomen];
                for(int i = 1; i <= colomen; i++){
                    Object obj = Result.getObject(i);
                    rij[i-1] = (obj==null) ?null:obj.toString();
                }
                tabel.add(rij);

            }
            for (String[] row:tabel){
                for (String s: row){
                    System.out.println(" " + s);
                }
                System.out.println();
            }
        }catch (Exception e){System.out.println(e);};

        for (int i = 0; i <5; i++){
            dataset.addValue(15, "Gelopen tijd", "22-12-2022");
        }
        JFreeChart chart = ChartFactory.createBarChart("\n \n Gelopen tijd in min", "Dag", "Tijd", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(900, 570));


        this.setContentPane(chartPanel);


    }

    public void createGraph(Gebruiker user){
        //Graph demo = new Graph("Lopen");
        this.pack();
        UIUtils.centerFrameOnScreen(this);

        JButton Terug = new JButton("Terug");
        Terug.setHorizontalAlignment(SwingConstants.LEFT);
        Terug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HetScherm scherm = new HetScherm();;
                scherm.HomePage(user);
            }
        });
        this.add(Terug);
        this.setVisible(true);

    }

}
