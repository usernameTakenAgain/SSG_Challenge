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
import java.util.Arrays;
import java.util.List;

public class Graph extends ApplicationFrame {
    public String[] rij;
    List<String[]> tabel = new ArrayList<>();
    public Graph(String title) {
        super(title);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        MySQLDB SqlConnectie = new MySQLDB();
        SqlConnectie.GetLoopGedrag();
        ResultSet Result = SqlConnectie.getResultSet();
        try {
            int colomen = Result.getMetaData().getColumnCount();
            while (Result.next()){
                this.rij = new String[colomen];
                for(int i = 1; i <= colomen; i++){
                    Object obj = Result.getObject(i);
                    rij[i-1] = (obj==null) ?null:obj.toString();
                }
                tabel.add(rij);

            }
        }catch (Exception e){System.out.println(e);};
        for(int x = 0; x<5; x++){
            String[] StringSplit = Arrays.toString(tabel.get(x)).split(",",-2);
            dataset.addValue(Double.parseDouble(StringSplit[1].toString()), "Gelopen tijd", StringSplit[0].toString().substring(1,11));
        }

        JFreeChart chart = ChartFactory.createBarChart("\n \n Gelopen tijd in min", "Dag", "Tijd", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1100, 570));


        this.setContentPane(chartPanel);


    }

    public void createGraph(Gebruiker user){
        Graph demo = new Graph("Lopen");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);

        JButton Terug = new JButton("Terug");
        Terug.setHorizontalAlignment(SwingConstants.LEFT);
        Terug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                demo.setVisible(false);
                HetScherm scherm = new HetScherm();;
                scherm.HomePage(user);

            }
        });
        demo.add(Terug);
        demo.setVisible(true);

    }

}
