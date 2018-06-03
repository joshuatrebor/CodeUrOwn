import javax.swing.*;
import java.io.*;
import java.util.*;

public class Bandal11{

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
output += "Bib Number: " +bibNumber;

output += "Distance: " +distance;

output += "Name: " +name;

output += "Age: " +age;

output1[ctr] = output;
ctr++;
}
if(input == 2){
JOptionPane.showMessageDialog(null, bibNumber);

output+="Distance: "+ distance;

output+="Name of Marathoner: "+name;

output+="Age: "+ age;

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
output = bibNumber + " " + distance + " " + name + " " + age;
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