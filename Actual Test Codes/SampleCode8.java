import javax.swing.*;
import java.io.*;
import java.util.*;

public class Sample{

	public static void main(String[] args)throws FileNotFoundException, IOException{

		Object possibleChoices[] = {"Choose here...", "Enroll a Marathoner", "View all marathons", "Erase all marathoners", "Update information", "Exit"};
		String name = "";
		int BibNo;
		int intAge = 0;
		String input = "";
		String output = "";
		String erase = "";
		
		while(true){
			Object selectedChoice = JOptionPane.showInputDialog(null, "Choose one", "Menu", JOptionPane.INFORMATION_MESSAGE, null, possibleChoices, possibleChoices[0]);			

			if (selectedChoice == possibleChoices[0]){

				JOptionPane.showMessageDialog(null, "Invalid Request. Try again...");
			}

			else if (selectedChoice == possibleChoices[1]){
				//Creating a file
				input = "";
				String fileName = JOptionPane.showInputDialog("Enter a file name:");
				FileWriter fw = new FileWriter(fileName, true);
				BufferedWriter bw = new BufferedWriter(fw);
				
				try{
					selectedChoice = possibleChoices[1]; 
					BibNo = (int)(10001+Math.random()*1000);//di pa final ang line na'to
					JOptionPane.showMessageDialog(null,"Please REMEMBER your BIB Number! \n\n Your Bib Number is " +BibNo);
					name = JOptionPane.showInputDialog("Enter your name:");
					intAge = Integer.parseInt(JOptionPane.showInputDialog("Enter your age: "));
					String distance[] = {"21KM-Half Mary", "42KM-Full Mary", "50KM-Ultra Marathon", "60KM-Ultra Marathon", "250KM-Ultra Marathon"};
					String strDistance = JOptionPane.showInputDialog("Choose here:\n[1] 21km\n[2] 42km\n[3] 50km\n[4] 60km\n[5] 250km");
					int intDistance = Integer.parseInt(strDistance);
					if (intDistance == 1){
						strDistance = distance[0];
					}
					else if (intDistance == 2){
						strDistance = distance[1];
					}
					else if (intDistance == 3){
						strDistance = distance[2];
					}

					else if (intDistance == 4){
						strDistance = distance[3];
					}

					else if (intDistance == 5){
						strDistance = distance[4];
					}

					else{
						JOptionPane.showMessageDialog(null,"Invalid Request!");
					}
					
					input = input + "BibNumber:" + "  " + BibNo + "  " + "Name:" + "  " + name +"  "+ "Age:" + "  " + intAge + "  " + "Distance:" + "  " + strDistance;
					
					bw.write(input);
					bw.newLine();
		      			bw.flush();
				
				}		
				catch(NumberFormatException e){}
				
				fw.close();
				bw.close();
			}

			else if (selectedChoice == possibleChoices[2]){
				//Reading a file
				try{
					output = "";
					String fileName = JOptionPane.showInputDialog("Enter a file name:");
					File file = new File(fileName);
					Scanner scanner = new Scanner(file);
				
					while(scanner.hasNext()){
						output+=scanner.nextLine()+"\n\n";
					}	
					JOptionPane.showMessageDialog(null, output);
				}
				catch(FileNotFoundException e){}
			}

			else if (selectedChoice == possibleChoices[3]){
				//Erase File Contents
				String fileName = JOptionPane.showInputDialog("Enter a file name:");
				FileWriter fw = new FileWriter(fileName);
				BufferedWriter bw = new BufferedWriter(fw);	
				boolean loopChecker = true;
				while(loopChecker){
					
					JOptionPane.showMessageDialog(null,"Record Successfully Cleared!");
					bw.flush();
					loopChecker = false;
					
				}	
				fw.close();
				bw.close();
				
			}

			else if (selectedChoice == possibleChoices[4]){
				//Update an info from a file and save it
				String fileName = JOptionPane.showInputDialog("Enter a file name:");
				Scanner sc = new Scanner(fileName);
				File file =new File(fileName);
				File tempFile = new File("tempLogFile.txt");// temp file
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
				JOptionPane.showInputDialog("Enter file to update: ");
				String lineToRemove=sc.nextLine();
				String Line;
				while((Line = br.readLine()) != null) {
					if(Line.contains(lineToRemove)){
						System.out.println(Line);
					}
					if(Line.contains(lineToRemove)) continue;
					bw.write(Line + System.getProperty("line.separator"));
				}
				bw.close();
				br.close();

				file.delete();
				tempFile.renameTo(file);

				//call yung nagwwrite :)
				bw.close();
				br.close();
			}

			else if (selectedChoice == possibleChoices[5]){
				System.exit(0);
			}
		}//while
	}//main
}//class