import javax.swing.*;
import java.io.*;
import java.util.*;

public class Sample{	
	public static void main(String[] args)throws FileNotFoundException, IOException{
		int choice = 0;
		while(true){
			choice = Integer.parseInt(JOptionPane.showInputDialog("1.Enroll A Marathoner \n2.View All Marathoners \n3.Erase File Contents \n4.Update A Marathoner Information \n5.Exit"));		
				
				File file = new File("Information.csv");
				FileWriter fwrite = new FileWriter(file, true);
				BufferedWriter bwrite = new BufferedWriter(fwrite);	

			try{
				if(choice == 0){
					JOptionPane.showMessageDialog(null, "Invalid Input!", "ERROR!", JOptionPane.ERROR_MESSAGE);	
				}	
				else if(choice == 1){
					int bibNumber = (int)(10001+Math.random()*3000);	
					String distance = JOptionPane.showInputDialog("Enter Distance wanted 21km/42km/50km/60km/250km: ");
					String input = "";
						String Menu[] = {"21KM - Half Mary","42KM - Full Mary","50KM - Ultramarathon","60KM - Ultramarathon","250KM - Ultramarathon"};
						String select = JOptionPane.showInputDialog("Choose your Category:\n\n 1. 21KM - Half Mary\n 2. 42KM - Full Mary\n 3. 50KM - Ultramarathon\n 4. 60KM - Ultramarathon 5. 250KM - Ultramarathon");
						int km = Integer.parseInt(select);
						try{
							if(km==1){
								input = Menu[0];
							}
							else if(km==2){
								input = Menu[1];
							}
							else if(km==3){
								input = Menu[2];
							}
							else if(km==4){
								input = Menu[3];
							}
							else if(km==5){
								input = Menu[4];	
							}
						}
							catch(NumberFormatException e){
								JOptionPane.showMessageDialog(null, "Invalid Input!", "ERROR!", JOptionPane.ERROR_MESSAGE);
							}
					String fullName = JOptionPane.showInputDialog("Enter Full Name: ");
					String age = JOptionPane.showInputDialog("Enter Age: ");	
		
					bwrite.write(bibNumber+"  "+distance+"  "+fullName+"  "+age);
					bwrite.newLine();
					bwrite.flush();
					fwrite.close();
					bwrite.close();	
				}	
				else if(choice == 2){
					FileWriter files = new FileWriter("Information.csv");	
					Scanner scan = new Scanner(files);
					String output = "";
			
					while(scan.hasNext()){
					output+= scan.nextLine()+"\n\n";
					}
					JOptionPane.showMessageDialog(null, output);
				}
				else if(choice == 3){
					FileWriter fwrite2 = new FileWriter("Information.csv");
					BufferedWriter bwrite2 = new BufferedWriter(fwrite2);
					String temp=" ";
			
					fwrite2.write(temp);
					fwrite2.flush();
					fwrite2.close();
				
					JOptionPane.showMessageDialog(null, "Cleared File Contents");
				}
				else if(choice == 4){
					int bibNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Bib Number: "));
					Scanner scanner = new Scanner(new File("Information.csv"));
					BufferedWriter copy = new BufferedWriter(new FileWriter("InfomationUpdated.csv"));

					while(scanner.hasNext()){
					String readVal = scanner.nextLine();
					copy.write(readVal);
					copy.newLine();
					copy.flush();
					}
					copy.close();
				}	
				else if(choice == 5){
					System.exit(0);
				}	
					fwrite.close();
					bwrite.close();
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Invalid Input!", "ERROR!", JOptionPane.ERROR_MESSAGE);
			}		
		}	
	}
}