import java.io.*;
import javax.swing.*;
import java.util.*;
public class CaraonRobert11{

	public static void main(String[] args) throws FileNotFoundException, IOException{
	String fileName = JOptionPane.showInputDialog("Enter a file name: ");
	FileWriter fw = new FileWriter(fileName, true);
	BufferedWriter bw = new BufferedWriter(fw);	
	Object selectValue[]={"Choose one. . .","Enroll a Marathoner","View All Marathoners","Erase File Content","Update a marathoner Information","Exit"};
			boolean loopcheck=true;
			while(loopcheck){
				Object choiceValue=JOptionPane.showInputDialog(null, "Hello","Input",JOptionPane.INFORMATION_MESSAGE,null,selectValue,selectValue[0]);
				if (choiceValue==selectValue[0]){
					JOptionPane.showMessageDialog(null,"Sorry Invalid Input");
				}
				if(choiceValue==selectValue[1]){
		            String output="";
						
					int bib= (int)(10001 + Math.random() * 99999);
					JOptionPane.showMessageDialog(null,"Your bib number is: "+bib);
					String fn=JOptionPane.showInputDialog(null,"Enter full name: ");
					String dist=JOptionPane.showInputDialog(null,"Enter distance: 21KM- Half Mary, 42KM-Full Mary, 50KM-Ultramarathon, 60KM-Ultramarathon, 250KM Ultramarathon");
					String age=JOptionPane.showInputDialog(null,"Enter age: ");
				
					output+=bib+"     "+fn+"     "+dist+"     "+age;					
				{	
						bw.write(output);
						bw.newLine();
						bw.flush();
				}
				}
		
			if(choiceValue==selectValue[2]){
				try{
				File file = new File(fileName);
	            Scanner scanner = new Scanner(file);

	            String view ="";
	        while(scanner.hasNext()){
		       view +=scanner.nextLine()+"\n";
	
	}
	            JOptionPane.showMessageDialog(null, view);
	}       catch(FileNotFoundException f){
		
	}
			}
			if(choiceValue==selectValue[3]){
				
			     FileWriter filew = new FileWriter(fileName);
	             BufferedWriter bufferedw = new BufferedWriter(filew);	
				
			}	
				
				
            if(choiceValue==selectValue[5]){
			   System.exit(0);
	    }
    }
}		
}   


