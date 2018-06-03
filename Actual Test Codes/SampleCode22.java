import javax.swing.*;
import java.util.*;
import java.io.*;

public class Sample
{
	
	public static void main(String[] args)throws FileNotFoundException,IOException
	{
		
		
			Object choices[]={"Choices: ","Enroll a marathoner","View all marathoner(s)","Erase file contents","Update a marathoner information","Exit"};
			String filename=JOptionPane.showInputDialog("Enter a file name: ");
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw=new BufferedWriter(fw);
			
			boolean check=true;
			while(check)
			{
				
					Object choice=JOptionPane.showInputDialog(null, "Choose","Input",JOptionPane.INFORMATION_MESSAGE,null,choices,choices[0]);
					if(choice==choices[0])
					{
					JOptionPane.showMessageDialog(null,"Invalid Request!");	
					}
					if(choice==choices[1])//Enrolling
					{	
						
						String output="";
						
					int bib= (int)(10001 + Math.random() * 99999);
					JOptionPane.showMessageDialog(null,"Your bib number is: "+bib);
					String fn=JOptionPane.showInputDialog(null,"Enter full name: ");
					String dist=JOptionPane.showInputDialog(null,"Enter distance: 21KM- Half Mary, 42KM-Full Mary, 50KM-Ultramarathon, 60KM-Ultramarathon, 250KM Ultramarathon");
					String age=JOptionPane.showInputDialog(null,"Enter age: ");
				
					
					output+=bib+"     "+fn+"     "+dist+"     "+age;					
							

						{
						
						bw.write(output);
						bw.newLine();
						bw.flush();
						}
					
					
					}

				
					
					if(choice==choices[2])//viewing
					{
						try
						{
						String fileName = JOptionPane.showInputDialog("Enter file name to read: (For confirmation)");
						File file = new File(fileName);
						Scanner scanner = new Scanner(file);

						String output ="";
						while(scanner.hasNext()){
							output+=scanner.nextLine()+"\n";
						
						}
						JOptionPane.showMessageDialog(null, output);
						}catch(FileNotFoundException f){
					}
							
					}
					if(choice==choices[3])//erase contents
					{
					fw = new FileWriter(filename);
					JOptionPane.showMessageDialog(null,"All entries are cleared!");
					fw.close();
					}
					if(choice==choices[4])
					{
					
					}
					if(choice==choices[5])
					{
					fw.close();
					bw.close();
					System.exit(0);	
					}
			}
			
		
		
		
	}
	
}