import java.util.*;
import java.io.*;
import javax.swing.*;

public class Elopre11{

	public static void main(String[] args)throws FileNotFoundException, IOException{
	try{
	String output = "";
	int bibNumber = 10001;
	int distance2 = 0;
	int editBib = 0;	
	FileWriter fw = new FileWriter("marathoners.txt");
	BufferedWriter bw = new BufferedWriter(fw);
	File file = new File("marathoners.txt");
	Scanner scanner = new Scanner(file);
	Object options[] = {"Options...","Enroll a Marathoner","View All Marathoner","Erase File Contents","Update a Marathoner Information","Exit"}; 
	boolean loopChecker=true;
	
	while (loopChecker){
		
		Object select = JOptionPane.showInputDialog(null, "Marathon Program", "Input", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		
		if(select==options[0]){
			JOptionPane.showMessageDialog(null, "Error, please try again...");
		}
		else if(select==options[1]){	
			
			int bibNumber2 = bibNumber ++;
			String name = JOptionPane.showInputDialog("Enter Marathoner's FULL NAME: ");
			String distanceIn = JOptionPane.showInputDialog("Choose Distance:\n[1]21KM-Half Mary\n[2]42KM-Full Mary\n[3]50KM-Ultramarathon\n[4]60KM-Ultramarathon\n[5]250KM-Ultramarathon");
			String ageIn = JOptionPane.showInputDialog("Enter your AGE:");
			int age = Integer.parseInt(ageIn);
			int distance = Integer.parseInt(distanceIn);
				if(distance == 1){
					distance2 = 21;
				}
				else if(distance == 2){
					distance2 = 42;
				}
				else if(distance == 3){
					distance2 = 50;
				}
				else if(distance == 4){
					distance2 = 60;
				}
				else if(distance == 5){
					distance2 = 250;
				}
				else{
					
				}
			output += bibNumber2+"   "+distance2+"km"+"   "+name+"   "+age+"\n";
				
			JOptionPane.showMessageDialog(null,"Record Saved!");
			bw.write(output);
		    bw.newLine();
		    bw.flush();
		}
		
		else if(select==options[2]){
			JOptionPane.showMessageDialog(null,output);
		}
		else if(select==options[3]){
			output = "";
			JOptionPane.showMessageDialog(null,"File contents ERASED...");
		}
		else if(select==options[4]){
			
		}
		else if(select==options[5]){
			System.exit(0);
		}
	}
	fw.close();
	bw.close();
	}catch(NumberFormatException e){
		JOptionPane.showMessageDialog(null,"ERROR! Something's wrong...");
	}
	
	}
}