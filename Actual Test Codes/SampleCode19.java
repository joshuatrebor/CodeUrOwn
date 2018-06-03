import javax.swing.*;

import java.util.*;
import java.io.*;


public class Sample{
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		
	
		
		
		while(true){
		try{	
		int choice=Integer.parseInt(JOptionPane.showInputDialog("Enter the number of your choice:"+"\n"+"[1]Enroll a Runner"+"\n"+"[2]View all Runners"+"\n"+"[3]Erase all File Contents"+"\n"+"[4]Update a Runner's Information"+"\n"+"[5]Exit"));
		switch(choice){
		case 1:{
			
			
			
			FileWriter fw= new FileWriter("RunnersList.txt",true);
			BufferedWriter bw= new BufferedWriter(fw);
			while(true){
			
			String input="";
			int BibNumber=	(int)(10001+Math.random()*19999);
			String DistanceChoices[]={"21Km-Half Mary","42Km - Full Mary", "50Km - UltraMarathon", "60km -UltraMarathon", "250Km- UltraMarathon"};
			
			String FullName=JOptionPane.showInputDialog("Enter your Full Name:");
		
			int age=Integer.parseInt(JOptionPane.showInputDialog("Enter your age"));
			
			String DistanceMenu=(String) JOptionPane.showInputDialog(null,"Choose a Distance....","Distance Choices", JOptionPane.QUESTION_MESSAGE,null,DistanceChoices,DistanceChoices[0]);
			
			input+="Bib Number:"+BibNumber+"Distance:"+DistanceMenu+"Name:"+FullName+"Age:"+age;
			bw.write(input);
			bw.newLine();
			bw.newLine();
			bw.flush();
			
			bw.close();
			fw.close();
			break;
				}	
			}
			
			
			
		
			
		
		
						
		
		
		case 2:{
			File run = new File("RunnersList.txt");
			Scanner scan = new Scanner(run);

			String output ="";
			while(scan.hasNext()){
				output+=scan.nextLine()+"\n";
			}
			JOptionPane.showMessageDialog(null, output);
			
			break;
			
		}
		case 3:{
			FileWriter delete = new FileWriter("RunnersList.txt");
			BufferedWriter clear = new BufferedWriter(delete);	
			
				
				JOptionPane.showMessageDialog(null,"File Contents Successfully Deleted!");
				clear.flush();
				delete.close();
				clear.close();
				break;
		}
		case 4:{
			while(true){
			int BibID=Integer.parseInt(JOptionPane.showInputDialog("Enter the Bib Number to be updated:"));
			Scanner scan = new Scanner(new File("RunnersList.txt"));
			BufferedWriter copy= new BufferedWriter(new FileWriter("RunnersListcopy.txt"));
			
			while(scan.hasNext()){
				String readVal = scan.nextLine();
				copy.write(readVal);
				copy.newLine();
				copy.flush();
			
			}
			copy.close();
			
			FileWriter delete = new FileWriter("RunnersListcopy.txt");
			BufferedWriter clear = new BufferedWriter(delete);	
			
			while(true){
				clear.flush();	
			
			delete.close();
			clear.close();
			
		
		FileWriter fw= new FileWriter("RunnersList.txt",true);
		BufferedWriter bw= new BufferedWriter(fw);
		while(true){
		
		String input="";
		int BibNumber=	(int)(10001+Math.random()*19999);
		String DistanceChoices[]={"21Km-Half Mary","42Km - Full Mary", "50Km - UltraMarathon", "60km -UltraMarathon", "250Km- UltraMarathon"};
		
		String FullName=JOptionPane.showInputDialog("Enter your Full Name:");
	
		int age=Integer.parseInt(JOptionPane.showInputDialog("Enter your age"));
		
		String DistanceMenu=(String) JOptionPane.showInputDialog(null,"Choose a Distance....","Distance Choices", JOptionPane.QUESTION_MESSAGE,null,DistanceChoices,DistanceChoices[0]);
		
		input+="Bib Number:"+BibNumber+"Distance:"+DistanceMenu+"Name:"+FullName+"Age:"+age;
		bw.write(input);
		bw.newLine();
		bw.newLine();
		bw.flush();
		
		
		bw.close();
		fw.close();
		break;
				
					}
				}
			}
		}
						
			
			
			
			
			
		
		case 5:{
			System.exit(0);
		}
		
		}
		
			
		
		
			
		}catch (NumberFormatException k){
			JOptionPane.showMessageDialog(null,"Mali boy!");
				}
		
			}
	
		}
	}
