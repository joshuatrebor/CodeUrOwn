import java.io.*;
import javax.swing.*;
import java.util.*;

public class RonnieSantos{
		

	public static void main(String[] args)throws FileNotFoundException, IOException{
		
		int Pick = 0;
		int bib = 1001;
		String Output = "";
		int BibNumber=0;
		ArrayList<String> list = new ArrayList<String>();
		

while(true){
	Pick = Integer.parseInt(JOptionPane.showInputDialog("1.Register a Marathoner \n2.View all Marathoner and Deatails \n3.Erase all Data \n4.Update the details \n5.Exit"));		
				
		File file = new File("Marathon.txt");
		FileWriter fwrite = new FileWriter(file,true);
		BufferedWriter bwrite = new BufferedWriter(fwrite);	

try{

	if(Pick == 0){
			JOptionPane.showMessageDialog(null, "Sorry,Invalid Entry");
		
		}	

	else if(Pick == 1){
		

		BibNumber = bib;		

		String Distance = JOptionPane.showInputDialog("Enter wanted Distance 21km/42km/ 0km/60km /250km :");

		String Fullname = JOptionPane.showInputDialog("Enter Your Full Name : ");

		String Age = JOptionPane.showInputDialog("Enter your Age : ");	

		
		bwrite.write(BibNumber+"  "+Distance+"  "+Fullname+"  "+Age);
		bwrite.newLine();
		bwrite.flush();
		fwrite.close();
		bwrite.close();	

			
BibNumber= BibNumber + bib++;


	}	

	else if(Pick == 2){
			
			
			File files = new File("Marathon.txt");
			Scanner scan = new Scanner(files);
			String output= "";		
		
		
		while(scan.hasNext()){
			
				 	
		output+= scan.nextLine()+"\n\n";

}
		JOptionPane.showMessageDialog(null, output);

		
							
	
}

	else if(Pick==3){

			
			Scanner scan = new Scanner(new File("Marathon.txt"));
			FileWriter fwrites = new FileWriter(file);
			BufferedWriter bwrites = new BufferedWriter(fwrites);
			String tempo=" ";
			
			 
			 fwrites.write(tempo);
			 fwrites.flush();
			 fwrites.close();
				
		JOptionPane.showMessageDialog(null, "Clear was Success");
	}
		
	else if(Pick == 4){
		//update details
		


		}
			
	else if(Pick == 5){
				System.exit(0);

}	


		fwrite.close();
		bwrite.close();
}
catch(IOException e){}		
  
		
		
		}//while
			
	}//main

}//class