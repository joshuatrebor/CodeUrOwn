/*
 * ANTI-JAVA SOURCE CODE PLAGIARSIM
 * USING STRING MATCHING AND
 * SEQUENTIAL SEARCH ALGORITHMS
 */
package codeurown;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.io.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * 
 * @authors
 * Castañeda, Joshua Robert B.
 * Militar, Jessica Justine E.
 * Zubiri, Ceasar Jem Rick D.
 * Fiestada, Angelo F.
 */
public class UI extends JFrame implements ActionListener, ChangeListener, PropertyChangeListener{
    
    private Parser parser = null;
    private JFileChooser fileChooser = new JFileChooser();
    private ArrayList<File> files = new ArrayList<>();
    private int pPercentage = 75;
    
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel topPanel =new JPanel();
    private JPanel midPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel botPanel = new JPanel(new GridLayout(2, 0, 0, 0));
        private JPanel topBotPanel = new JPanel();
        private JPanel botBotPanel = new JPanel();
    
    private JLabel lblTitle = new JLabel("Code Ur Own");
    private JLabel lblSubTitle = new JLabel("(an Anti-Java Source Code Plagiarism Program)");
    private JLabel lblProgress = new JLabel("Progress:");
    private JButton btnAdd = new JButton("Add");
    private JButton btnRemove = new JButton("Remove");
    private JButton btnRemoveAll = new JButton("Remove All");
    private JButton btnStart = new JButton("Start");
    private JButton btnClose = new JButton("Close");
    private JProgressBar progressBar = new JProgressBar(0, 100);
    private JList fileList = new JList();
    private DefaultListModel listModel = new DefaultListModel();
    private JScrollPane listScroll;
    private JSlider percentSlide = new JSlider();
    private JLabel lblPercent = new JLabel("75%");
    private JLabel lblPCaption = new JLabel("Plagiarism Percentage:");
    
    UI(Parser parser){
        this.parser = parser;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLocationRelativeTo(null);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Java Files", "java"));
        fileChooser.setCurrentDirectory(new File("Actual Test Codes"));
        progressBar.setValue(0);
        progressBar.setStringPainted(true);       
        
        listScroll = new JScrollPane(fileList);
        listScroll.setPreferredSize(new Dimension(300, 250));
        fileList.setVisibleRowCount(15);
        fileList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        fileList.setModel(listModel);
        fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        percentSlide.setMaximum(100);
        percentSlide.setMinimum(0);
        percentSlide.setValue(75);
        percentSlide.addChangeListener(this);
        
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblSubTitle.setFont(new Font("Arial", Font.PLAIN, 15));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnStart.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnStart.setEnabled(false);
        
        midPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 1), "Java Source Codes", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 15)));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        topPanel.add(lblTitle, Box.CENTER_ALIGNMENT);
        topPanel.add(lblSubTitle, Box.CENTER_ALIGNMENT);
        topPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        midPanel.add(listScroll);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(btnAdd, Box.RIGHT_ALIGNMENT);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(btnRemove);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(btnRemoveAll);
        topBotPanel.add(lblPCaption);
        topBotPanel.add(percentSlide);
        topBotPanel.add(lblPercent);
        botBotPanel.add(btnStart);
        botBotPanel.add(lblProgress);
        botBotPanel.add(progressBar);
        botBotPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        botBotPanel.add(btnClose);
        botPanel.add(topBotPanel);
        botPanel.add(botBotPanel);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(midPanel, BorderLayout.CENTER);
        mainPanel.add(botPanel, BorderLayout.SOUTH);
        add(mainPanel);
        
        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
        btnRemoveAll.addActionListener(this);
        btnStart.addActionListener(this);
        btnClose.addActionListener(this);
        
        decorate();
        pack();
        setVisible(true);
    }
    
    public void decorate(){
        topPanel.setBackground(Color.LIGHT_GRAY);
        botPanel.setBackground(Color.LIGHT_GRAY);
        botBotPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.setBackground(Color.lightGray);
        Font font = new Font("Tahoma", Font.PLAIN, 14);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblSubTitle.setFont(new Font("Tahome", Font.PLAIN, 16));
        lblProgress.setFont(font);
        btnAdd.setFont(font);
        btnStart.setFont(font);
        btnRemove.setFont(font);
        btnRemoveAll.setFont(font);
        lblPercent.setFont(font);
        lblPCaption.setFont(font);
        btnClose.setFont(font);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnAdd){
            fileChooser.showOpenDialog(this);
            File[] file = fileChooser.getSelectedFiles();
            for(File f: file){
                files.add(f);
                listModel.addElement(f.getName());
            }
            btnStart.setEnabled(!files.isEmpty());
        }
        else if(e.getSource() == btnRemove){
            java.util.List<File> rFiles = fileList.getSelectedValuesList();
            for(Object o: rFiles){
                listModel.removeElement(o);
            }
            files.removeAll(rFiles);
            btnStart.setEnabled(!files.isEmpty());
            
        }
        else if(e.getSource() == btnRemoveAll){
            listModel.removeAllElements();
            files.removeAll(files);
            btnStart.setEnabled(!files.isEmpty());
        }
        else if(e.getSource() == btnStart){
            try{
                btnStart.setEnabled(false);
                PlagDetector pd = new PlagDetector(files, pPercentage);
                pd.addPropertyChangeListener(this);
                pd.execute();
            }catch(IOException f){}
        }
        else if(e.getSource() == btnClose){
            System.exit(0);
        }
    }

    public void stateChanged(ChangeEvent e) {
        lblPercent.setText(percentSlide.getValue() + "%");
        pPercentage = percentSlide.getValue();
    }

    public void propertyChange(PropertyChangeEvent e) {
        if("progress" == e.getPropertyName()){
            Integer i = (Integer) e.getNewValue();
            progressBar.setValue(i);
            if(i == 0)
                btnStart.setEnabled(true);
        }
    }
}
            
