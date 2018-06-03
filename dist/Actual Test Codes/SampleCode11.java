/*
 * Drixler Angelo B. Historillo
 * BSCS 2-1
 * 
 * Creation Date:       11:52, 30 Jul 2015
 * Completion Date:     20:09, 31 Jul 2015
 */
package historillo11;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Historillo11 {

    public static Object possibleChoices1[] = {"Enroll a Marathoner", "View All Marathoners", "Erase File Contents", "Update a Marathoner Information", "Exit"};
    public static Object possibleChoices2[] = {"21KM - Half Mary", "42KM - Full Mary", "50KM - Ultramarathon", "60KM - Ultramarathon", "250KM - Ultramarathon", "Back"};
    public static String titleName = "Marathon Application", fileName = "MarathonApplication.csv";
    public static String[][] arrData = new String[10][4];
    
        
    public static void main(String[] args) throws FileNotFoundException, IOException, NumberFormatException, ArrayIndexOutOfBoundsException{
        boolean loop1 = true;
        
        FileWriter fw = new FileWriter(fileName, true);
        FileWriter fw2 = new FileWriter(fileName);
        Scanner scanner = new Scanner(new File(fileName));
        String output = "";
        int bibNo, index = 0, row, column;
        
        do {
            Object selectedChoice1 = JOptionPane.showInputDialog(null, "", titleName, JOptionPane.PLAIN_MESSAGE, null, possibleChoices1, possibleChoices1[0]);
            
            if(selectedChoice1 == possibleChoices1[0]) {
                bibNo = (int) (1 + (Math.random() * 100001));
                output = StoreData1();
                output = output + "," + StoreData2();
                output = bibNo + "," + output + "\n";
                fw.write(output);
                fw.flush();
                fw.close();
                output = "";
            }
            else if (selectedChoice1 == possibleChoices1[1]) {
                while(scanner.hasNext()){
                    String[] input = scanner.nextLine().split(",");
                    arrData[index][0] = input[0];
                    arrData[index][1] = input[1];
                    arrData[index][2] = input[2];
                    arrData[index][3] = input[3];
                    index++;
                }
                
                for (row = 0; row < arrData.length; row++) {
                    for(column = 0; column < arrData[row].length; row++) {
                        output += arrData[row][column] + " - ";
                    }
                    output += "\n";
                }
                JOptionPane.showMessageDialog(null, output, titleName, JOptionPane.PLAIN_MESSAGE);
                
                output = "";
            }
            else if (selectedChoice1 == possibleChoices1[2]) {
                output = "";
                fw2.write(output);
                fw2.flush();
                fw2.close();
            }
            else if (selectedChoice1 == possibleChoices1[3]) {
                
                while(scanner.hasNext()) {
                    String[] input1 = scanner.nextLine().split(",");
                    
                    arrData[index][0] = input1[0];
                    index++;
                }
                
                String findNo = JOptionPane.showInputDialog(null, "Enter your Bib number: ", titleName, JOptionPane.PLAIN_MESSAGE);
                for (row = 0; row < arrData.length; row++) {
                    if(arrData[row][0].equals(findNo)) {
                        arrData[row][1] = StoreData1();
                        arrData[row][2] = JOptionPane.showInputDialog(null, "Enter your full name: ", titleName, JOptionPane.PLAIN_MESSAGE);
                        arrData[row][3] = JOptionPane.showInputDialog(null, "Enter your age: ", titleName, JOptionPane.PLAIN_MESSAGE);
                    }
                }
                
                for (row = 0; row < arrData.length; row++) {
                    for(column = 0; column < arrData[row].length; row++) {
                        output += arrData[row][column] + ",";
                    }
                    output += "\n";
                }
                fw2.write(output);
                fw2.flush();
                fw2.close();
                output += "";
            }
            else if (selectedChoice1 == possibleChoices1[4]) {
                loop1 = false;
            }
            else {
                JOptionPane.showMessageDialog(null, "Request denied.", titleName, JOptionPane.ERROR_MESSAGE);
            }
        } while(loop1 == true);
    }
    
    public static String StoreData1() {
        boolean loop2 = true;
        String output1 = "";
        
        do {
            Object selectedChoice2 = JOptionPane.showInputDialog(null, "", titleName, JOptionPane.PLAIN_MESSAGE, null, possibleChoices2, possibleChoices2[0]);
            
            if (selectedChoice2 == possibleChoices2[0]) {
                output1 = (String) selectedChoice2;
                loop2 = false;
            }
            else if (selectedChoice2 == possibleChoices2[1]) {
                output1 = (String) selectedChoice2;
                loop2 = false;
            }
            else if (selectedChoice2 == possibleChoices2[2]) {
                output1 = (String) selectedChoice2;
                loop2 = false;
            }
            else if (selectedChoice2 == possibleChoices2[3]) {
                output1 = (String) selectedChoice2;
                loop2 = false;
            }
            else if (selectedChoice2 == possibleChoices2[4]) {
                output1 = (String) selectedChoice2;
                loop2 = false;
            }
            else if (selectedChoice2 == possibleChoices2[5]) {
                loop2 = false;
            }
            else {
                JOptionPane.showMessageDialog(null, "Request denied.", titleName, JOptionPane.ERROR_MESSAGE);
            }
        } while(loop2 == true);
        
        return output1;
    }
    
    public static String StoreData2() {
        String fullName, age;
        
        fullName = JOptionPane.showInputDialog(null, "Enter your full name: ", titleName, JOptionPane.PLAIN_MESSAGE);
        age = JOptionPane.showInputDialog(null, "Enter your age: ", titleName, JOptionPane.PLAIN_MESSAGE);
        
        String output2 = fullName + "," + age;
        
        return output2;
    }
}