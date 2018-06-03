import java.io.*;
import java.util.*;
import javax.swing.*;

public class Batalla11{
		static int Select = 0;
	public static void main(String []args) throws FileNotFoundException, IOException{
		while(Select != 5){
			Select = Integer.parseInt(JOptionPane.showInputDialog("[1]Enroll a Marathoner \n[2]View all Marathoner \n[3]Erase File Contents \n[4]Update a Marathoner Information \n[5]Exit"));

			switch(Select){
				case 1:{
					enroll();
				}break;
				case 2:{
					view();
				}break;
				case 3:{
					erase();
				}break;
				case 4:{
					update();
				}break;
			}
		}
	}

	public static void enroll() throws IOException{
		String filename = JOptionPane.showInputDialog("Enter a Filename: ");
		FileWriter fw = new FileWriter(filename, true);
		BufferedWriter bw = new BufferedWriter(fw);

			String distance = JOptionPane.showInputDialog("21KM- Half Mary, 42KM-Full Mary, 50KM-Ultramarathon, 60KM-Ultramarathon, 250KM Ultramarathon" + "\nChoose Distance:");
			String fullname = JOptionPane.showInputDialog("Enter your full name: ");
			String age = (JOptionPane.showInputDialog("Enter your age : "));
		
		bw.write(distance);
		bw.write(fullname);
		bw.write(age);
		bw.newLine();
		bw.flush();
	}

	public static void view() throws FileNotFoundException{
		String fileName = JOptionPane.showInputDialog("Enter file name to view:");
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);

		String output ="";
		while(scanner.hasNext()){
			output+=scanner.nextLine()+"\n";
		}
		JOptionPane.showMessageDialog(null, output);
	}

	public static void erase() throws FileNotFoundException{
		String fileName = JOptionPane.showInputDialog("Enter file name:");
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);

		String output ="";
		JOptionPane.showMessageDialog(null, output);
	}

	public static void update(){
		
	}

}