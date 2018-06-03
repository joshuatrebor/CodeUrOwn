import javax.swing.*;
import java.io.*;
import java.util.*;
public class Sablan11{
private static int bibnum=0;
private static int distance=0;
private static String name="";
private static String fname="";
private static String mname="";
private static String sname="";
private static int age=0;

public static void main(String args[])throws FileNotFoundException, IOException{

Object selectValue[]={"Choose one. . .","1-Enroll","2-View all","3-Erase File Contents","4-Update A Marathoner Info","5-Exit"};
		boolean loopcheck=true;		 
		FileWriter fw = new FileWriter("Sablan11.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);	

		while(loopcheck){
		Object choiceValue=JOptionPane.showInputDialog(null,"Marathon","Input",JOptionPane.INFORMATION_MESSAGE,null,selectValue,selectValue[0]);	
		if (choiceValue==selectValue[0]){
					JOptionPane.showMessageDialog(null,"Invalid Input");
		}
		if(choiceValue==selectValue[1]){
			String output = "";
			 bibnum=(int)(10001+Math.random()*99999);
			 String val= JOptionPane.showInputDialog("Choose Distance:\n[1]-21km\n[2]-42km\n[3]-50km\n[4]-60km\n[5]-250km");
			 int temp= Integer.parseInt(val);
			 if(temp==1)
				 distance = 21;
			 else if(temp==2)
				 distance = 42;
			 else if(temp==3)
				 distance = 50;
			 else if(temp==4)
				 distance = 60;
			 else if(temp==5)
				 distance = 250;
			 else 
				 JOptionPane.showMessageDialog(null,"Must enter only from the choices!");
			 fname= JOptionPane.showInputDialog("Enter First Name: ");
			 mname= JOptionPane.showInputDialog("Enter Middle Name: ");
			 sname= JOptionPane.showInputDialog("Enter Last Name: ");
			 String v = JOptionPane.showInputDialog("Enter Age: ");
			 age = Integer.parseInt(v);
			name += fname + " " + mname + " " + sname;
			
			output += "Bib Number:" +bibnum+"\n" +" Distance:" +distance+"km" + "\n"+" Name:" +name+ "\n" + " Age:" +age;
			
			bw.write(output);
			bw.newLine();
			bw.flush();
		}//enroll
		if(choiceValue==selectValue[2]){
			File file = new File("Sablan11.txt");
			Scanner scanner = new Scanner(file);
			String output ="";
			while(scanner.hasNext()){
			output+=scanner.nextLine()+"\n";
			}
		JOptionPane.showMessageDialog(null, output);
	
			

		}
		if(choiceValue==selectValue[3]){
			FileWriter erase = new FileWriter("Sablan11.txt");
			BufferedWriter clear=new BufferedWriter(erase);
			boolean loop=true;
			while(loop){
				JOptionPane.showMessageDialog(null,"File Contents Successfully Erased");
				clear.flush();
				loop=false;
			}
			erase.close();
			clear.close();
			
	

		}
		if(choiceValue==selectValue[4]){
		
		int tempb=0, selection=JOptionPane.NO_OPTION;
		boolean found=false;
		do{
			try{
				tempb = Integer.parseInt(JOptionPane.showInputDialog("Enter your Bib number:"));
			}
			catch(NumberFormatException e){
				selection = JOptionPane.showConfirmDialog(null, "Invalid Input!\nDo you want to try again?","Try Again",JOptionPane.YES_NO_OPTION);
			}
		}while(selection == JOptionPane.YES_OPTION);
		
		File file = new File("Sablan11.txt");
		File tempFile = new File("tempFile.txt");
		Scanner scanner = new Scanner(file);
		BufferedWriter write = new BufferedWriter(new FileWriter("tempFile.txt"));
			while(scanner.hasNext()){
				String details = scanner.nextLine();
				if(tempb!=bibnum){
					write.write(details);
					write.newLine();
					write.flush();
				}
				else if(tempb==bibnum){
					String output = "";
			 bibnum=(int)(10001+Math.random()*99999);
			 String val= JOptionPane.showInputDialog("Choose Distance:\n[1]-21km\n[2]-42km\n[3]-50km\n[4]-60km\n[5]-250km");
			 int temp= Integer.parseInt(val);
			 if(temp==1)
				 distance = 21;
			 else if(temp==2)
				 distance = 42;
			 else if(temp==3)
				 distance = 50;
			 else if(temp==4)
				 distance = 60;
			 else if(temp==5)
				 distance = 250;
			 else 
				 JOptionPane.showMessageDialog(null,"Must enter only from the choices!");
			 fname= JOptionPane.showInputDialog("Enter new First Name: ");
			 mname= JOptionPane.showInputDialog("Enter new Middle Name: ");
			 sname= JOptionPane.showInputDialog("Enter new Last Name: ");
			 String v = JOptionPane.showInputDialog("Enter new  Age: ");
			 age = Integer.parseInt(v);
			name += fname + " " + mname + " " + sname;
			
			output += "Bib Number:" +bibnum+"\n" +" Distance:" +distance+"km" + "\n"+" Name:" +name+ "\n" + " Age:" +age;
					write.write(output);
					write.newLine();
					write.flush();
					found=true;
					JOptionPane.showMessageDialog(null,"Marathoner Details Updated!");
				}
			}
			
			write.close();
			scanner.close();
			if(found==false){
				JOptionPane.showMessageDialog(null,"Bib number "+tempb+" is not found!");
			}
			else if (found==true){
				file.delete();
				tempFile.renameTo(new File("Sablan11.txt"));
			}
			else
				JOptionPane.showMessageDialog(null,"Operation Failed");
		}
		if(choiceValue==selectValue[5]){
					System.exit(0);
		
		}
		
		fw.close();
		bw.close();
		
	}
}





}