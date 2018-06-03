import java.io.*;
import  javax.swing.JOptionPane;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.FileOutputStream;
public class Sample{
	public static void main(String args[]) throws FileNotFoundException, IOException{
		int check=0;
	int bibNum=0;
	int bibNum2=0;
		String menu="[1]Enroll a Marathoner\n[2]View all Marathoners\n[3]Erase Record\n[4]Update Marathoners Information\n[5]Exit";
		boolean loop=true;
		Enroll enrollNow=new Enroll();
		while(loop){
			int input=Integer.parseInt(JOptionPane.showInputDialog(menu));
			if (input==1){
	FileWriter fw = new FileWriter("marathon.txt",true);
	BufferedWriter bw = new BufferedWriter(fw);	
		 enrollNow.getLastName();
		      bw.write("Full Name:  " +enrollNow.lastName);
		      bw.flush();
			enrollNow.getFirstName();
			 bw.write(" "+enrollNow.firstName);
		      bw.flush();
			  enrollNow.getMiddleName();
			   bw.write(" "+enrollNow.MiddleName);
		     bw.newLine();
		      bw.flush();
			  try{
				  enrollNow.getAge();
				  bw.write("Age : "+enrollNow.age);
		      bw.newLine();
		      bw.flush();
			  }catch(NumberFormatException e){
				  JOptionPane.showMessageDialog(null,"Invalid Age","ERROR",JOptionPane.ERROR_MESSAGE);
				  break;
			  }
			  Random rand=new Random();
			  bibNum=rand.nextInt(2002)+1001;
			  String a="Your BIB Number is\n";
			  bibNum2=Integer.parseInt(JOptionPane.showInputDialog(a+bibNum,"Please enter your BIB Number:"));
			    bw.write("BIB Number : "+bibNum);
		      bw.newLine();
		      bw.flush();
			fw.close();
			bw.close();
			
			}
			else if(input==2){
				File file=new File("marathon.txt");
				Scanner scan=new Scanner(file);
				String output="";
				while(scan.hasNext()){
					output+=scan.nextLine()+"\n";
				}
				JOptionPane.showMessageDialog(null, output);
	}
	else if(input==3){
				FileWriter fw = new FileWriter("marathon.txt",false);
					JOptionPane.showMessageDialog(null,"Record has Been Cleared");
				}
				else if(input==4){
				check=Integer.parseInt(JOptionPane.showInputDialog(null," Please enter your BIB number :"));
				}//ftjf
				else if(input==5){
					System.exit(0);
				}
				}
		}//while(loop)
	}// main