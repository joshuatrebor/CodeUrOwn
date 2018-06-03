import java.io.*;
import javax.swing.*;
import java.util.*;

public class Rigodon11{

	public static void main(String[] args) throws FileNotFoundException, IOException{
	
	String fileName = JOptionPane.showInputDialog("Enter a file name:");
	FileWriter fw = new FileWriter(fileName,true);
	Scanner scanner=new Scanner(new File(fileName));
	BufferedWriter bw = new BufferedWriter(fw);	
	boolean loopChecker=true;
	String[][]arr=new String [5][4];
	int index=0;
	String output="";



	try{while(loopChecker){
			int selection=Integer.parseInt(JOptionPane.showInputDialog("[1]Enter marathoner\n[2]View all marathoner\n[3]Erase file content\n[4]Edit Marathoner\n[5]Exit"));
			if (selection == 1){
			String name=JOptionPane.showInputDialog("Enter Full Name");
			String distance=JOptionPane.showInputDialog("What Distance\n(21km-Half Mary; 42km-Full Mary; 50km-Ultramarathon; 250km-Ultramarathon)");
			int age=Integer.parseInt(JOptionPane.showInputDialog("Enter age"));
			int bibNumber=(int)((Math.random()*1000)+1);
			output=bibNumber+"-"+distance+"-"+name+"-"+age;
			bw.write(output);
			bw.newLine();
			bw.flush();
			fw.close();
			bw.close();
	  		
			}
			if (selection==2){
				while(scanner.hasNext())
					output+=scanner.nextLine()+"\n";
				JOptionPane.showMessageDialog(null,output);
			}
			if (selection==3){
				FileWriter fw1= new FileWriter(fileName);
				BufferedWriter bw1 = new BufferedWriter(fw1);
				bw.write("");
				bw.flush();
				JOptionPane.showMessageDialog(null,"Done");
			}
			if (selection==4){
				boolean ck=true;
				String bibNum=JOptionPane.showInputDialog("Enter Bib number ");
				while(scanner.hasNext()){
					String readVal=scanner.nextLine();
					if (readVal.equals(bibNum)){
					System.out.print(output);
						
					}				
				}	
			}
			if (selection==5){
				System.exit(0);
			}
		}
	} catch(NumberFormatException e){}

	}	
	
}

