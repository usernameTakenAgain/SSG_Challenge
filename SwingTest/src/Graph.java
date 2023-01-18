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

public class Graph extends ApplicationFrame {
    public Graph(String title) {
        super(title);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(15, "Gelopen tijd", "22-12-2022");
        dataset.addValue(25, "Gelopen tijd", "23-12-2022");
        dataset.addValue(10, "Gelopen tijd", "24-12-2022");
        JFreeChart chart = ChartFactory.createBarChart("\n \n Gelopen tijd in min", "Dag", "Tijd", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));


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
