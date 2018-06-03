import javax.swing.*;

public class Lim11{

	public static void main(String[] args)throws IOException{

		int ctr = 0;
		int ctr1 = 0;
		int age = 0;
		int bibNumber = 10000;
		String distance = "";
		String output = "";
		String output2 = "";
		String name = "";
		String[] output1 = new String[1000];
	
		FileWriter fw = new FileWriter("marathon.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		int bibNumber2 = 0;
		String menu = "[1]Enroll a Marathoner\n[2]View All Marathoners\n[3]Erase File Contents\n[4]Update A Marathoner Information\n[5]Exit";		
		boolean loop = true;
		while(loop){
			int input = Integer.parseInt(JOptionPane.showInputDialog(menu));
			if(input == 1){
				bibNumber++;
				distance = JOptionPane.showInputDialog("Enter distance(21KM- Half Mary, 42KM-Full Mary, 50KM-Ultramarathon, 60KM-Ultramarathon, 250KM Ultramarathon): ");
				name = JOptionPane.showInputDialog("Enter full name:");
				age = Integer.parseInt(JOptionPane.showInputDialog("Enter age: "));
 				output += JOptionPane.showMessageDialog(null, "Bib Number: " +bibNumber);
				BufferedWriter.newLine(); 
				output += JOptionPane.showMessageDialog(null, "Distance: " +distance);
				BufferedWriter.newLine();
				output += JOptionPane.showMessageDialog(null, "Name: " +name);
				BufferedWriter.newLine();
				output += JOptionPane.showMessageDialog(null, "Age: " +age);
				BufferedWriter.newLine();
						output1[ctr] = output;
						ctr++;
				}
			if(input == 2){
				JOptionPane.showMessageDialog(null, bibnumber);

				output+="Distance: "+ distance;
				BufferedWriter.newLine(); 
				output+="Name of Marathoner: "+ firstName+" "+middleName+" "+lastName;
				BufferedWriter.newLine(); 
				output+="Age: "+ age;
				BufferedWriter.newLine(); 	
				JOptionPane.showMessageDialog(null,output);
				}	

			if(input == 3){
			for(ctr1 = 0; ctr1 < 1000; ctr1++){
						output1[ctr1] = "";
						output2 = "";
					}
					JOptionPane.showMessageDialog(null, "Record was cleared!");
				
			}

			if(input == 4){
			bibNumber2 = Integer.parseInt(JOptionPane.showInputDialog("Enter the Bib Number: "));
					bibNumber = bibNumber2;
					bibNumber2 = 10001;
					distance = JOptionPane.showInputDialog("Enter the distance: ");
					name = JOptionPane.showInputDialog("Enter full name");
					age = Integer.parseInt(JOptionPane.showInputDialog("Enter age: "));
					output = bibNumber + " " + choice + " " + name + " " + age;
					output1[bibNumber2] = output;
			}	
				
			if(input == 5){	
			System.exit(0);
			}

		for(ctr1 = 0; ctr1 < ctr; ctr1++){
				
				bw.write(output1[ctr1]);
				bw.append("\r\n");
			}
			bw.flush();
	fw.close();
	 bw.close();
}
}
}