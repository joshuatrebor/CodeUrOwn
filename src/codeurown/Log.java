/*
 * ANTI-JAVA SOURCE CODE PLAGIARSIM
 * USING STRING MATCHING AND
 * SEQUENTIAL SEARCH ALGORITHMS
 */
package codeurown;
import java.awt.Dimension;
import javax.swing.*;
/**
 * 
 * @authors
 * Castañeda, Joshua Robert B.
 * Militar, Jessica Justine E.
 * Zubiri, Ceasar Jem Rick D.
 * Fiestada, Angelo F.
 */
public class Log extends JFrame{
   Log(String log){
       setSize(new Dimension(400, 700));
       JTextArea txtLog = new JTextArea(log);
       JScrollPane scroll = new JScrollPane(txtLog);
       add(new JLabel("Log"));
       add(scroll);
       setVisible(true);
   } 
}
