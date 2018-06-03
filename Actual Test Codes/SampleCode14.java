import javax.swing.*;
import java.io.*;
import java.util.*;

public class Sample{
	public static void main(String[] args)throws FileNotFoundException, IOException, NumberFormatException{
		Object possibleValues[] ={"Choose here...","Enroll a Marathoner","View all Marathoner","Erase File Contents","Update Marathoner Information","Exit"}; 
		boolean loopChecker=true;
		while(loopChecker){
			Object selectedValue = JOptionPane.showInputDialog(null, "Choose an action", "Marathoners Program", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
			Periabras11D method = new Periabras11D();
			Periabras11C en = new Periabras11C();
			if(selectedValue==possibleValues[0]){
				JOptionPane.showMessageDialog(null, "Invalid Request!");
			}
			else if (selectedValue==possibleValues[1]){//Enroll a marathoner
				en.enroll();		
			}
			else if (selectedValue==possibleValues[2]){//view all marathoner
				method.viewAll();
			}
			else if (selectedValue==possibleValues[3]){//erase file contents
				method.eraseAll();		
			}
			else if (selectedValue==possibleValues[4]){//update information
				method.update();
			}
			else if (selectedValue==possibleValues[5]){
				int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Exit",JOptionPane.YES_NO_OPTION);
				if(selection == JOptionPane.YES_OPTION)
					System.exit(0);								
			}
		}
	}
}