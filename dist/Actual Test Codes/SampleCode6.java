import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
public class Castaneda10{
	
	private static Marathoner[] marathoners = new Marathoner[1000];
	private static int mCtr = 0;
	
	public static void main(String args[]){
		try{
			loadMarathoners();
		}catch(FileNotFoundException e){}
		int choice = 0;
		do{
			try{
				choice = Integer.parseInt(JOptionPane.showInputDialog("[1]Enroll a Marathoner\n[2]View All Marathoners\n[3]Erase File Contents\n[4]Update a Marathoner\n[5]Exit"));
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Invalid Input", "Marathon", JOptionPane.ERROR_MESSAGE);
			}
			switch(choice){
				case 1: {
					String lName = JOptionPane.showInputDialog("Enter your Last Name");
					String fName = JOptionPane.showInputDialog("Enter your First Name");
					String mName = JOptionPane.showInputDialog("Enter your Middle Name");
					boolean loop = true;
					int age = 0;
					while(loop){
						try{
							age = Integer.parseInt(JOptionPane.showInputDialog("Enter your Age: "));
							if(age <= 0 || age > 100)
								JOptionPane.showMessageDialog(null, "Invalid Age!", "Marathoner", JOptionPane.ERROR_MESSAGE);
							else
								loop = false;
						}catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null, "Invalid Input!", "Marathoner", JOptionPane.ERROR_MESSAGE);
						}
					}
					Object[] kms = {"Half Mary (21km)", "Full Mary (42km)", "Ultramarathon (50km)", "Ultramarathon (60km)", "Ultramarathon(250)"};
					Object distance = JOptionPane.showInputDialog(null, "Choose a Distance: ", "Marathon", JOptionPane.INFORMATION_MESSAGE, null, kms, kms[0]);
					marathoners[mCtr] = new Marathoner(lName, fName, mName, (String)distance, age);
					try{
						FileWriter writer = new FileWriter("marathon.csv", true);
						writer.write(lName + "," + fName + "," + mName + "," + age + "," + marathoners[mCtr++].getBibNo() + "," + distance + "\r\n");
						writer.close();
					}catch(IOException f){}
					JOptionPane.showMessageDialog(null, "Marathoner Information Successfully Enrolled\n\n" + marathoners[mCtr-1].getInfo());
					break;
				}
				case 2: {
					if(mCtr == 0){
						JOptionPane.showMessageDialog(null, "There are no Marathoners to Display");
						break;
					}
					String infos = "Marathoners       " + "# of Marathoners: " + mCtr + "\n\n";
					for(int i = 0; i < mCtr; i++){
						infos += marathoners[i].getInfo();
					}
					JOptionPane.showMessageDialog(null, infos);
					break;
				}
				case 3: {
					if(mCtr == 0){
						JOptionPane.showMessageDialog(null, "File already empty");
						break;
					}
					mCtr = 0;
					try{
						FileWriter writer = new FileWriter("marathon.csv");
						writer.close();
					}catch(IOException e){}
					JOptionPane.showMessageDialog(null, "File Contents Successfully Erased");
					break;
				}
				case 4: {
					if(mCtr == 0){
						JOptionPane.showMessageDialog(null, "There are no Marathoners to be Updated");
						break;
					}
					int bibNo = 0;
					boolean loop = true;
					while(loop){
						try{
							bibNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Bib No: "));
						}catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null, "Invalid Input", "Marathon", JOptionPane.ERROR_MESSAGE);
							continue;
						}
						loop = false;
					}
					int i = 0;
					for(i = 0; i < mCtr; i++){
						if(marathoners[i].getBibNo() == bibNo)
							break;
					}
					if(i == mCtr)
						JOptionPane.showMessageDialog(null, "Marathoner not found",  "Marathon", JOptionPane.ERROR_MESSAGE);
					else{
						String lName = JOptionPane.showInputDialog("Current Name: " + marathoners[i].getLName() + "\nEnter new Last Name: ");
						String fName = JOptionPane.showInputDialog("Current Name: " + marathoners[i].getFName() + "\nEnter new First Name: ");
						String mName = JOptionPane.showInputDialog("Current Name: " + marathoners[i].getMName() + "\nEnter new Middle Name: ");
						marathoners[i].setName(lName, fName, mName);
						int age = 0;
						do{
							age = Integer.parseInt(JOptionPane.showInputDialog(null, "Current Age: " + marathoners[i].getAge() + "\nEnter new age: "));
						}while(!marathoners[i].setAge(age));
						Object[] kms = {"Half Mary (21km)", "Full Mary (42km)", "Ultramarathon (50km)", "Ultramarathon (60km)", "Ultramarathon(250)"};
						Object distance = JOptionPane.showInputDialog(null, "Current Distance: "+ marathoners[i].getDistance() + "\nChoose new Distance: ", "Marathon", JOptionPane.INFORMATION_MESSAGE, null, kms, kms[0]);
						marathoners[i].setDistance((String)distance);
						try{
							FileWriter writer = new FileWriter("marathon.csv");
							for(int j = 0; j < mCtr; j++)
								writer.write(marathoners[j].getLName() + "," + marathoners[j].getFName() + "," + marathoners[j].getMName() + "," + marathoners[j].getAge() + "," + marathoners[j].getBibNo() + "," + marathoners[j].getDistance() + "\r\n");
								writer.close();
						}catch(IOException e){}
						JOptionPane.showMessageDialog(null, "Marathoner Information Successfully Updated\n\n" + marathoners[i].getInfo());
					}
				}
			}
		}while(choice != 5);
	}
	
	
	
	public static void loadMarathoners() throws FileNotFoundException{
		Scanner scanner = new Scanner(new File("marathon.csv"));
		String temp[] = new String[7];
		while(scanner.hasNext()){
			temp = scanner.nextLine().split(",");
			marathoners[mCtr++] = new Marathoner(temp[0], temp[1], temp[2], temp[5], Integer.parseInt(temp[3]));
		}
	}
}