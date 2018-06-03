/*
 * ANTI-JAVA SOURCE CODE PLAGIARSIM
 * USING STRING MATCHING AND
 * SEQUENTIAL SEARCH ALGORITHMS
 */
package codeurown;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
/**
 * 
 * @authors
 * Castañeda, Joshua Robert B.
 * Militar, Jessica Justine E.
 * Zubiri, Ceasar Jem Rick D.
 * Fiestada, Angelo F.
 */
public class Graph extends JFrame implements ActionListener{
    
    
    class GPanel extends JPanel implements MouseListener{
        
        private final int originX = 40;
        private final int originY = 360;
        private ArrayList<ArrayList<Integer>> x;
        private ArrayList<String> names;
        private ArrayList<Color> colors = new ArrayList<>();
        private ArrayList<JLabel> labels = new ArrayList<>();
        private int clickedLine = -1;
        private int highLight = -1;
        
        GPanel(ArrayList<ArrayList<Integer>> x, ArrayList<String> names){
            this.setPreferredSize(new Dimension(720, 400));
            this.setBackground(Color.white);
            this.x = x;
            this.names = names;
            Random rnd = new Random();
            for(int i = 0; i < names.size(); i++)
                colors.add(new Color(rnd.nextInt(200), rnd.nextInt(200), rnd.nextInt(200)));
            drawLegend();
        }
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(Color.black);
            
            g2.setStroke(new BasicStroke(3));
            g2.drawLine(originX, originY, originX, originY - 340);
            g2.drawLine(originX, originY, originX + 640, originY);
            
            g2.setStroke(new BasicStroke(1));
            
            int ox = originX;
            int oy = originY;
            int sX = 700;
            int sY = 40;
            
            int ctr = 0;
            for(ArrayList<Integer> ax : x){
                g2.setColor(colors.get(ctr++));
                ox = originX;
                oy = originY;
                int xMax = ax.stream().mapToInt(e -> e.intValue()).sum();
                int yMax = ax.size();
                int moveY = 340/yMax;
                //System.out.println("x: " + xMax + "\ny: " + yMax);
                for(Integer x : ax){
                    int moveX = (640/xMax)*x;
                    g2.drawLine(ox, oy, ox + moveX, oy - moveY);
                    //System.out.println(ox + "," + oy + "   " + (ox+moveX) + "," + (oy-moveY));
                    ox += moveX;
                    oy -= moveY;
                }
            }
            if(highLight != -1){
                ArrayList<Integer> ax = x.get(highLight);
                g2.setStroke(new BasicStroke(3));
                g2.setColor(colors.get(highLight));
                ox = originX;
                oy = originY;
                int xMax = ax.stream().mapToInt(e -> e.intValue()).sum();
                int yMax = ax.size();
                int moveY = 340/yMax;
                for(Integer x : ax){
                    int moveX = (640/xMax)*x;
                    g2.drawLine(ox, oy, ox + moveX, oy - moveY);
                    //System.out.println(ox + "," + oy + "   " + (ox+moveX) + "," + (oy-moveY));
                    ox += moveX;
                    oy -= moveY;
                }
            }
            //System.out.println("Hello");
        }
        
        public void drawLegend(){
            midRightPanel.removeAll();
            int nameI = 0;
            for(Color color : colors){
                JLabel tmpLabel = new JLabel(names.get(nameI++));
                tmpLabel.setForeground(color);
                tmpLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                midRightPanel.add(tmpLabel);
                tmpLabel.addMouseListener(this);
                labels.add(tmpLabel);
            }
        }
        
        public void mouseClicked(MouseEvent e){
            int i = labels.indexOf(e.getSource());
            clickedLine = i;
            repaint();
        }
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
        public void mouseEntered(MouseEvent e){
            int i = labels.indexOf(e.getSource());
            highLight = i;
            repaint();
            JLabel lbl = (JLabel)e.getSource();
            lbl.setFont(new Font("Arial", Font.BOLD, 18));
        }
        public void mouseExited(MouseEvent e){
            JLabel lbl = (JLabel)e.getSource();
            lbl.setFont(new Font("Arial", Font.ITALIC, 15));
            highLight = clickedLine;
            repaint();
        }
    }
    private ArrayList<Cluster> clusters;
    
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel topPanel = new JPanel();
    private JPanel midPanel = new JPanel(new BorderLayout());
        private JPanel midCenPanel = new JPanel();
        private JPanel midRightPanel = new JPanel();
        private JPanel midBotPanel = new JPanel();
    private JPanel botPanel = new JPanel();
    
    private JLabel lblTitle = new JLabel("Result");
    private JButton btnClose = new JButton("Close");
    private JButton btnView = new JButton("View Log");
    private JScrollPane legendScroll;
    private String log;
    
    Graph(ArrayList<ArrayList<Integer>> x, ArrayList<String> names, ArrayList<Cluster> clusters, String log){
        //System.out.println(x.size() + "   " + names.size());
        GPanel graphPanel = new GPanel(x, names);
        this.clusters = clusters;
        this.log = log;
        graphPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        legendScroll.setPreferredSize(new Dimension(1000, 400));
        midRightPanel.setLayout(new BoxLayout(midRightPanel, BoxLayout.PAGE_AXIS));
        midCenPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 3), "Program Structures Graph", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 17), Color.black));
        legendScroll = new JScrollPane(midRightPanel);
        legendScroll.setPreferredSize(new Dimension(200, 20));
        legendScroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 2), "Legend", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 20), Color.black));
        JScrollPane clusterScroll = new JScrollPane(midBotPanel);
        
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(lblTitle);
        midCenPanel.add(graphPanel);
        midPanel.add(midCenPanel, BorderLayout.CENTER);
        midPanel.add(legendScroll, BorderLayout.EAST);
        midPanel.add(clusterScroll, BorderLayout.SOUTH);
        botPanel.add(btnView);
        btnView.addActionListener(this);
        botPanel.add(btnClose);
        btnClose.addActionListener(this);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(midPanel, BorderLayout.CENTER);
        mainPanel.add(botPanel, BorderLayout.SOUTH);
        add(mainPanel);
        addClusters();
        
        decorate();
        setSize(new Dimension(980, 700));
        setVisible(true);
    }
    
    public void decorate(){
        topPanel.setBackground(Color.LIGHT_GRAY);
        botPanel.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Tahoma", Font.PLAIN, 14);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnView.setFont(font);
        btnClose.setFont(font);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnClose)
            this.dispose();
        else if(e.getSource() == btnView)
            new Log(log);
    }
    
    public void addClusters(){
        for(Cluster cluster : clusters){
            JPanel panel = new JPanel();
            JPanel panel2 = new JPanel();
            JScrollPane scroll = new JScrollPane(panel2);
            scroll.setPreferredSize(new Dimension(130, 90));
            
            panel.setPreferredSize(new Dimension(150, 120));
            panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), cluster.toString(), TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14)));
            panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
            panel2.setBackground(Color.white);
            
            for(String name : cluster){
                panel2.add(new JLabel(name));
            }
            panel.add(scroll);
            midBotPanel.add(panel);
        }
    }
    
}
