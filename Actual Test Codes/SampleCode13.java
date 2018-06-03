import javax.swing.*;
import java.io.*;
import java.util.*;

public class Sample{
	public static String input = "";
	public static void main(string[] args)throws FileNotFoundException, IOException{
		boolean menuLoop = true;
		while(menuLoop){
			try{
				String SelectInput = JOptionPane.showInputDialog("1. Enroll a Marathoner\n 2. View all Marathoners\n 3. Erase File Contents\n 4. Udpate a Marathoner Information\n 5. Exit");
				int menuInput = Integer.parseInt(SelectInput);
				if (menuInput==1){
					FileWriter fw = new FileWriter("List.csv", true);
					BufferedWriter bw = new BufferedWriter(fw);
					boolean loop2 = true;
					while(loop2){
						int BibNumber = (int)(10001+Math.random()*3000);
						JOptionPane.showMessageDialog(null, "Your Bib Number is:" + BibNumber);
						String Menu[] = {"21KM - Half Mary","42KM - Full Mary","50KM - Ultramarathon","60KM - Ultramarathon","250KM - Ultramarathon"};
						String select = JOptionPane.showInputDialog("Choose your Category:\n\n 1. 21KM - Half Mary\n 2. 42KM - Full Mary\n 3. 50KM - Ultramarathon\n 4. 60KM - Ultramarathon 5. 250KM - Ultramarathon");
						int km = Integer.parseInt(select);
						try{
							if(km==1){
								intinput = Menu[0];
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
							catch(NumberFormatException e){}
							String display = "";
							String first = JOptionPane.showInputDialog("Enter First Name: ");
							String last = JOptionPane.showInputDialog("Enter Last Name: ");
							int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age:")); 
							if (age>=60){
								JOptionPane.showMessageDialog(null,"Age is invalid!","INAVLID",JOptionPane.ERROR_MESSAGE);
							}
							Display+="BIB NUMBER:"+BibNumber+"\nDISTANCE: "+input+"\n"+"FULL NAME: " +last+ "," + " " +first + "\r\n" + "AGE: "+ age;
							bw.write(Display);
							bw.newLine();
							bw.newLine();
							bw.flush();
							loop2= false;
						
					
							}
									
						}
					
					fw.close();
					bw.close();
				
				
				
				if(MenuInput==2){
					
					File marathon = new File("List.csv");
					Scanner scanner = new scanner(marathon);
					
					String output = "";
					while(scanner.hasNext()){
					output+=scanner.nextLine()+"\n";
				}
				JOptionPane.showMessageDialog(null, output);
					
					
				}
				else if(MenuInput==3){
					FileWriter erase = new FileWriter("List.csv");
					BufferedWriter clear = new BufferedWriter(erase);	
					boolean loop3 = true;
					while(loop3){
						JOptionPane.showMessageDialog(null,"File Contents Successfully Erased!");
						clear.flush();
						loop3= false;
					
				}	
				erase.close();
				clear.close();
				}
				else if(MenuInput==4){
					int Bib= Integer.parseInt(JOptionPane.showInputDialog("Enter your Bib Number: "));
					Scanner scanner = new Scanner(new File("List.csv"));
					BufferedWriter copy= new BufferedWriter(new FileWriter("ListCopy.csv"));

					while(scanner.hasNext()){
						String readVal = scanner.nextLine();
						copy.write(readVal);
						copy.newLine();
						copy.flush();
				}
				}
				copy.close();
				
				FileWriter erase = new FileWriter("ListCopy.csv");
				BufferedWriter clear = new BufferedWriter(erase);	
				boolean loop3 = true;
				while(loop3){
					clear.flush();
					loop3= false;
				}	
				erase.close();
				clear.close();
				
				FileWriter fw = new FileWriter("ListCopy.csv", true);
				BufferedWriter bw = new BufferedWriter(fw);
				boolean loop2 = true;
				while(loop2){
					
					
					String Menu[]= {"21KM - Half Mary","42KM - Full Mary","50KM - Ultramarathon","60KM - Ultramarathon","250KM - Ultramarathon"};
					
					String select = JOptionPane.showInputDialog("PLEASE CHOOSE YOUR CATEGORY:\n\n[1] 21KM-HALF MARY\n[2] 42KM-FULL MARY\n[3] 50KM-ULTRAMARATHON\n[4] 60KM-ULTRAMARATHON\n[5] 250KM-ULTRAMARATHON");
					int kilometer = Integer.parseInt(select);
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
							}catch(NumberFormatException e){}
							String Display="";
							String first = JOptionPane.showInputDialog("Enter First Name: ");	
					String last = JOptionPane.showInputDialog("Enter Last Name: ");

					int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age: "));
						Display+="BIB NUMBER:"+Bib+"\nDISTANCE: "+input+"\n"+"FULL NAME: "+last+","+" "+first +"AGE: "+age;
						bw.write(Display);
						bw.newLine();
						bw.newLine();
						bw.flush();
						loop2= false;
					
				}	
				fw.close();
				bw.close();
				Scanner scanner1 = new Scanner(new File("ListCopy.csv"));
				BufferedWriter copy1= new BufferedWriter(new FileWriter("List.csv",true));
				
					while(scanner1.hasNext()){
					String readVal1 = scanner1.nextLine();
					copy1.write(readVal1);
					copy1.newLine();
					copy1.flush();
				}
				
				JOptionPane.showMessageDialog(null, "File Updated Successfully!");
				copy1.close();
				
			}catch(NumberFormatException e){}
			else if(MenuInput==5){
				System.exit(0);
				
			}
			
			}
			
		}

}