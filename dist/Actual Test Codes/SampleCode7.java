import javax.swing.*;
import java.util.*;
import java.io.*;

public class DelMundo11{
	public static void main(String[] args) throws IOException{
		 
		int choice = 0;
		int ctr = 0;
		int ctr2 = 0;
		int bibNum = 10000;
		int distance;
		String fullName;
		int age;
		String displayDistance;
		String output;
		String output2 = ""; 
		int x = 0;	
		
		FileWriter list = new FileWriter("Marathon.txt");
		
		int num = Integer.parseInt(JOptionPane.showInputDialog("How many will be joining the marathon? "));
		
		String[] marathoner = new String[num];
		
		while(choice != 5){
		 choice = Integer.parseInt(JOptionPane.showInputDialog("[1] Enroll a Marathoner\n [2] View all Marathoner\n [3] Erase File Content\n [4] Update a Marathoner Information\n [5] Exit"));
			switch(choice){
				case 1:{
					bibNum += 1;
					distance = Integer.parseInt(JOptionPane.showInputDialog("[1] 21Km - Half Mary\n [2] 42Km - Full Mary\n [3] 50Km - Ultramarathon\n [4] 60Km - Ultramarathon\n [5] 	250Km - Ultramarathon\n "));
					fullName = JOptionPane.showInputDialog("Enter your fullname: ");
					age = Integer.parseInt(JOptionPane.showInputDialog("How old are you? "));
					if(distance == 1){
						displayDistance = "21Km - Half Mary";
					}
					else if(distance == 2){
						displayDistance = "42Km - Full Mary";
					}
					else if(distance == 3){
						displayDistance = "50Km - Ultramarathon";
					}
					else if(distance == 4){
					displayDistance = "60Km - Ultramarathon";
					}
					else{
						displayDistance = "250Km - Ultramarathon";
					}
					output = bibNum + "     " + displayDistance + "     " + fullName + "     " + age;
					marathoner[ctr] = output;
					ctr++;
				}break;
			
				case 2:{
					for(x = 0; x < ctr; x++){
							output2 += marathoner[x] + "\n";
					}
					JOptionPane.showMessageDialog(null, output2);
					output2 = "";
				}break;
				
				case 3:{
					for(x = 0; x < num; x++){
							marathoner[x] = "";
							output2 = "";
					}
					JOptionPane.showMessageDialog(null, "The list of marathoners was cleared!");
					ctr = 0;
					bibNum = 10000;
				}break;
			
				case 4:{
					ctr2 = Integer.parseInt(JOptionPane.showInputDialog("Enter the Bib Number: "));
					bibNum = ctr2;
					ctr2 -= 10001;
					distance = Integer.parseInt(JOptionPane.showInputDialog("[1] 21Km - Half Mary\n [2] 42Km - Full Mary\n [3] 50Km - Ultramarathon\n [4] 60Km - Ultramarathon\n [5] 	250Km - Ultramarathon\n "));
					fullName = JOptionPane.showInputDialog("Enter your fullname: ");
					age = Integer.parseInt(JOptionPane.showInputDialog("How old are you? "));
					if(distance == 1){
						displayDistance = "21Km - Half Mary";
					}
					else if(distance == 2){
						displayDistance = "42Km - Full Mary";
					}
					else if(distance == 3){
						displayDistance = "50Km - Ultramarathon";
					}
					else if(distance == 4){
						displayDistance = "60Km - Ultramarathon";
					}
					else{
						displayDistance = "250Km - Ultramarathon";
					}
					output = bibNum + "     " + displayDistance + "     " + fullName + "     " + age;
					marathoner[ctr2] = output;
				}break;
			}
		}
			for(x = 0; x < ctr; x++){
				list.write(marathoner[x]);
				list.append("\r\n");
			}
			list.flush();
	}
}