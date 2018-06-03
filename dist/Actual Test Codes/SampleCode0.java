import java.io.*;
import javax.swing.*;
import java.util.*;



public class Atienza11{
	public static String input ="";
	public static void main(String[] args)throws FileNotFoundException, IOException{
		boolean MenuLoop= true;
		while(MenuLoop){
		try{
		String SelectInput = JOptionPane.showInputDialog("[1] Enroll a Runner\n[2] View all Runners\n[3] Erase File Contents\n[4] Update a Runner Information\n[5] Exit");
			int MenuInput=Integer.parseInt(SelectInput);
			if (MenuInput==1){
				
				FileWriter fw = new FileWriter("Runners.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				boolean loop2 = true;
				while(loop2){
					
					int BibNumber = (int)(10001+Math.random()*80000); 
					JOptionPane.showMessageDialog(null,"Your Bib Number is: " +BibNumber);
					String Menu[]= {"21KM-HALF MARY","42KM-FULL MARY","50KM-ULTRAMARATHON","60KM-ULTRAMARATHON","250KM-ULTRAMARATHON"};
					
					String selectMenu = JOptionPane.showInputDialog("PLEASE CHOOSE YOUR CATEGORY:\n\n[1] 21KM-HALF MARY\n[2] 42KM-FULL MARY\n[3] 50KM-ULTRAMARATHON\n[4] 60KM-ULTRAMARATHON\n[5] 250KM-ULTRAMARATHON");
					int kilometer = Integer.parseInt(selectMenu);
					try{
					if(kilometer==1){
						input = Menu[0];
					}
					else if(kilometer==2){
						input = Menu[1];
					}
					else if(kilometer==3){
						input = Menu[2];
					}
					else if(kilometer==4){
						input = Menu[3];
					}
					else if(kilometer==5){
						input = Menu[4];
					}
					} catch(NumberFormatException e){}
					String Output="";
					String first = JOptionPane.showInputDialog("Enter First Name: ");
					String midInput = JOptionPane.showInputDialog("Enter Middle Name: ");
					char mid = midInput.charAt(0);
					String last = JOptionPane.showInputDialog("Enter Last Name: ");
					
					try{
					int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age: "));
					if (age<10||age>=70){
						JOptionPane.showMessageDialog(null,"Age is invalid!","INVALID",JOptionPane.ERROR_MESSAGE);
						}	
						
						Output+="BIB NUMBER:"+BibNumber +"\nDISTANCE: "+input+"\nFULL NAME: "+ last+","+" "+ first+" "+ mid+"."+"\n"+"AGE: "+ age;
						bw.write(Output);
						bw.newLine();
						bw.newLine();
						bw.flush();
						loop2= false;
						
					}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Invalid Input.","ERROR",JOptionPane.ERROR_MESSAGE);
						}
				
				}
				fw.close();
				bw.close();
			}
			else if(MenuInput==2){
				
				File marathon = new File("Runners.txt");
				Scanner scanner = new Scanner(marathon);

				String output ="";
				while(scanner.hasNext()){
					output+=scanner.nextLine()+"\n";
				}
				JOptionPane.showMessageDialog(null, output);
				
			}
			else if(MenuInput==3){
				FileWriter erase = new FileWriter("Runners.txt");
				BufferedWriter clear = new BufferedWriter(erase);	
				boolean loop3 = true;
				while(loop3){
					
					JOptionPane.showMessageDialog(null,"The File Contents Successfully Erased!");
					clear.flush();
					loop3= false;
					
				}	
				erase.close();
				clear.close();
			}
			else if(MenuInput==4){
				int Bib= Integer.parseInt(JOptionPane.showInputDialog("Enter your Bib Number: "));
				Scanner scanner = new Scanner(new File("Runners.txt"));
				BufferedWriter replace= new BufferedWriter(new FileWriter("RunnersUpdate.txt"));
				
					while(scanner.hasNext()){
					String readVal = scanner.nextLine();
					replace.write(readVal);
					replace.newLine();
					replace.flush();
				}
				replace.close();
				
				FileWriter erase = new FileWriter("RunnersUpdate.txt");
				BufferedWriter clear = new BufferedWriter(erase);	
				boolean loop3 = true;
				while(loop3){
					clear.flush();
					loop3= false;
				}	
				erase.close();
				clear.close();
				
				FileWriter fw = new FileWriter("RunnersUpdate.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				boolean loop2 = true;
				while(loop2){
					
					
					String Menu[]= {"21KM-HALF MARY","42KM-FULL MARY","50KM-ULTRAMARATHON","60KM-ULTRAMARATHON","250KM-ULTRAMARATHON"};
					
					String selectMenu = JOptionPane.showInputDialog("PLEASE CHOOSE YOUR CATEGORY:\n\n[1] 21KM-HALF MARY\n[2] 42KM-FULL MARY\n[3] 50KM-ULTRAMARATHON\n[4] 60KM-ULTRAMARATHON\n[5] 250KM-ULTRAMARATHON");
					int kilometer = Integer.parseInt(selectMenu);
					try{
					if(kilometer==1){
						input = Menu[0];
					}
					else if(kilometer==2){
						input = Menu[1];
					}
					else if(kilometer==3){
						input = Menu[2];
					}
					else if(kilometer==4){
						input = Menu[3];
					}
					else if(kilometer==5){
						input = Menu[4];
					}
					} catch(NumberFormatException k){}
						String Output="";
						
					int BibNumber = (int)(10001+Math.random()*5000);	
						
					String first = JOptionPane.showInputDialog("Enter First Name: ");
					
					String midInput = JOptionPane.showInputDialog("Enter Middle Name: ");
						char mid = midInput.charAt(0);
					String last = JOptionPane.showInputDialog("Enter Last Name: ");

					int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age: "));
						Output+="BIB NUMBER:"+BibNumber +"\nDISTANCE: "+input+"\nFULL NAME: "+last+ ","+" "+first+ " "+mid+ "\n"+"AGE: "+age;
						
						
					}
				}
			else if(MenuInput==5){
				System.exit(0);
				}
			}catch(NumberFormatException e){}
		}
	}
}