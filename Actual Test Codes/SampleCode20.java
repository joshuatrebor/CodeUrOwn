import java.io.*;
import java.util.*;
import javax.swing.*;

public class Sample{

	// Enroll A Marathoner
	public static void doEnroll () throws FileNotFoundException, IOException {

		// declaration and initialization
		Object objDistanceInput = "";
		boolean boolCheckFlag1 = true, boolCheckFlag2 = true, boolCheckFlag3 = true;
		int intBibNumber = 0, intAge = 0;
		Object objDistanceList[] = {"21km - Half Mary", "42km - Full Mary", "50km - Ultramarathon", "60km - Ultramarathon", "250km - Ultramarathon"};
		String strFullName = "", strToWrite = "";

		while (true) {

			// distance input
			boolCheckFlag1 = true;
			if (boolCheckFlag1 == true && boolCheckFlag2 == true && boolCheckFlag3 == true) {

				try {
					objDistanceInput = JOptionPane.showInputDialog(null, "Please enter the distance you want:", "Enrollment (1/4)", JOptionPane.QUESTION_MESSAGE, null, objDistanceList, objDistanceList[0]);
				} catch (NullPointerException e) {
					//JOptionPane.showMessageDialog(null, "You have entered an invalid value", "Warning!", JOptionPane.ERROR_MESSAGE);
					boolCheckFlag1 = false;
				}

				if (objDistanceInput.equals("") == true){
					JOptionPane.showMessageDialog(null, "You have entered an invalid value", "Warning!", JOptionPane.ERROR_MESSAGE);
					boolCheckFlag1 = false;
				}

			}

			// full name input
			boolCheckFlag2 = true;
			if (boolCheckFlag1 == true && boolCheckFlag2 == true && boolCheckFlag3 == true) {
				strFullName = JOptionPane.showInputDialog(null, "Please enter your name:", "Enrollment (2/4)", JOptionPane.QUESTION_MESSAGE);

				if (strFullName.equals("") == true) {
					JOptionPane.showMessageDialog(null, "This field cannot be left empty!", "Warning!", JOptionPane.ERROR_MESSAGE);
					boolCheckFlag2 = false;
				}

			}

			// age input
			boolCheckFlag3 = true;
			if (boolCheckFlag1 == true && boolCheckFlag2 == true && boolCheckFlag3 == true) {
				try {
					intAge = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter your age:", "Enrollment (3/4)", JOptionPane.QUESTION_MESSAGE));
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "You have entered an invalid value", "Warning!", JOptionPane.ERROR_MESSAGE);
					boolCheckFlag3 = false;
				};
			}

			// random bib number auto generation
			if (boolCheckFlag1 == true && boolCheckFlag2 == true && boolCheckFlag3 == true) {
				intBibNumber = (int)(10001+Math.random()*9999);
				JOptionPane.showMessageDialog(null, "Your Bib Number is: " + intBibNumber);

				strToWrite = intBibNumber + "," + strFullName + "," + intAge + "," + objDistanceInput;

				// declaration and initialization
				FileWriter fwData = new FileWriter("data.csv", true);
				BufferedWriter bwData = new BufferedWriter(fwData);

				// write
				bwData.write(strToWrite);
				bwData.newLine();
				bwData.flush();

				// close
				fwData.close();
				bwData.close();

				JOptionPane.showMessageDialog(null, "You have successfully enrolled- \n\nBib Number: " + intBibNumber + "\nFull Name: " + strFullName + "\nAge: " + intAge + "\nDistance: " + objDistanceInput + "\n\n-to the list of marathoners.", "Action Completed", JOptionPane.INFORMATION_MESSAGE);
				break;

			}

		} // while (true)
	} // doEnroll


	// View All Marathoners
	public static void doView () throws FileNotFoundException, IOException {

		// declaration and initialization
		File fileData = new File("data.csv");
		Scanner scanData = new Scanner(fileData);

		String[][] arrData = new String[255][4];
		String strToDisplay = "";
		int intMarathonerCount = 0;

		// read and assignment
		for (int intRowCount = 0; scanData.hasNext(); intRowCount++) {
			String[] arrReadData = scanData.nextLine().split(",");
			for (int intColumnCount = 0; intColumnCount != 4; intColumnCount++) {
				arrData[intRowCount][intColumnCount] = arrReadData[intColumnCount];
			}
			intMarathonerCount++;
		}

		// if list is empty
		if (intMarathonerCount == 0) {
			JOptionPane.showMessageDialog(null, "The list of enrolled marathoners is empty.", "Action Completed", JOptionPane.INFORMATION_MESSAGE);

		// if list is not empty; message dialog display
		} else {
			for (int intRowCount = 0; intRowCount != intMarathonerCount; intRowCount++){
				strToDisplay += "[Marathoner #" + (intRowCount+1) + "]\nBib Number: " + arrData[intRowCount][0] + "\nDistance: " + arrData[intRowCount][3] + "\nFull Name: " + arrData[intRowCount][1] + "\n\n";
			}

			JOptionPane.showMessageDialog(null, strToDisplay, "List of Marathoners", JOptionPane.INFORMATION_MESSAGE);
		}

	} // doView

	// Erase File Contents
	public static void doClear () throws FileNotFoundException, IOException {
		FileWriter fwData = new FileWriter("data.csv");
		fwData.close();
		JOptionPane.showMessageDialog(null, "The records has been cleared.", "Action Failed", JOptionPane.WARNING_MESSAGE);
	} // doClear

	//
	public static void doUpdate() throws FileNotFoundException, IOException {
		// declaration and initialization
		File fileData = new File("data.csv");
		Scanner scanData = new Scanner(fileData);
		String[][] arrData = new String[255][4];
		String strToDisplay = "";
		int intMarathonerCount = 0, intBibNumberInput = 0, intMarathonerNumber = 0;
		boolean boolCheckFlag1 = true, boolCheckFlag2 = false, boolIfComplete = false;

		// read and assignment
		for (int intRowCount = 0; scanData.hasNext(); intRowCount++) {
			String[] arrReadData = scanData.nextLine().split(",");
			for (int intColumnCount = 0; intColumnCount != 4; intColumnCount++) {
				arrData[intRowCount][intColumnCount] = arrReadData[intColumnCount];
			}
			intMarathonerCount++;
		}

		// if list is empty
		if (intMarathonerCount == 0) {
			JOptionPane.showMessageDialog(null, "The list of enrolled marathoners is empty.", "Action Failed", JOptionPane.WARNING_MESSAGE);

		// if list is not empty
		} else {
			for (int intRowCount = 0; intRowCount != intMarathonerCount; intRowCount++){
				strToDisplay += "Marathoner #" + (intRowCount+1) + ": " + arrData[intRowCount][0] + "\n";
			}

			// if input is complete
			while (true) {
				if (boolIfComplete == true) {

					// declaration and initialization
					FileWriter fwData = new FileWriter("data.csv");
					BufferedWriter bwData = new BufferedWriter(fwData);
					String strToWrite[] = new String[intMarathonerCount];

					for (int intRowCount = 0; intRowCount != intMarathonerCount; intRowCount++) {
						strToWrite[intRowCount] = arrData[intRowCount][0] + "," + arrData[intRowCount][1] + "," + arrData[intRowCount][2] + "," + arrData[intRowCount][3];

						// write
						bwData.write(strToWrite[intRowCount]);
						bwData.newLine();
						bwData.flush();
					}

					// close
					fwData.close();
					bwData.close();
					JOptionPane.showMessageDialog(null, "The information has been updated.", "Action Completed", JOptionPane.INFORMATION_MESSAGE);
					break;
				}

				// update 1/3 - bib number input
				boolCheckFlag1 = true;
				try {
					intBibNumberInput = Integer.parseInt(JOptionPane.showInputDialog(null, "List of currently enrolled Marathoners:\n\n" + strToDisplay + "\nPlease select a Marathoner's Bib Number from the list.", "Update Information [1/3]", JOptionPane.QUESTION_MESSAGE));
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "You have entered an invalid value.", "Action Failed", JOptionPane.WARNING_MESSAGE);
					boolCheckFlag1 = false;
				}

				// check for match
				if (boolCheckFlag1 == true) {
					for (int intRowCount = 0; intRowCount != intMarathonerCount; intRowCount++) {
						if (intBibNumberInput == Integer.parseInt(arrData[intRowCount][0])) {
							//System.out.println("Match Found");
							boolCheckFlag2 = true;
							intMarathonerNumber = intRowCount;
						}
					}

					// if found
					if (boolCheckFlag2 == true) {
						try {
							Object objInfoList[] = {"Update Full Name", "Update Age", "Update Distance"};

							Object objInfoInput = JOptionPane.showInputDialog(null, "Please select from the drop-down list:", "Update Information [2/3]", JOptionPane.QUESTION_MESSAGE, null, objInfoList, objInfoList[0]);

							// update full name
							if (objInfoInput == objInfoList[0]) {
								while (true) {
									arrData[intMarathonerNumber][1] = JOptionPane.showInputDialog(null, "Enter a new full name:", "Update Information [3/3]", JOptionPane.QUESTION_MESSAGE);
									if (arrData[intMarathonerNumber][1].equals("") == true) {
										JOptionPane.showMessageDialog(null, "This field cannot be left empty.", "Action Failed", JOptionPane.WARNING_MESSAGE);
									} else {
										boolIfComplete = true;
										break;
									}
								}

							// update age
							} else if (objInfoInput == objInfoList[1]) {
								while (true) {
									try {
										int intTempAgeInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter a new age:", "Update Information [3/3]", JOptionPane.QUESTION_MESSAGE));
										arrData[intMarathonerNumber][2] = "";
										arrData[intMarathonerNumber][2] += intTempAgeInput;
										boolIfComplete = true;
										break;
									} catch (NumberFormatException e) {
										JOptionPane.showMessageDialog(null, "You have entered an invalid value.", "Action Failed", JOptionPane.WARNING_MESSAGE);
									}

								}

							// update distance
							} else if (objInfoInput == objInfoList[2]) {
								while (true) {
									String strTempDistanceInput = "";
									Object objDistanceList[] = {"21km - Half Mary", "42km - Full Mary", "50km - Ultramarathon", "60km - Ultramarathon", "250km - Ultramarathon"};
									Object objDistanceInput = JOptionPane.showInputDialog(null, "Enter a new distance", "Update Information [3/3]", JOptionPane.QUESTION_MESSAGE,null , objDistanceList, objDistanceList[0]);
									strTempDistanceInput += objDistanceInput;
									arrData[intMarathonerNumber][3] = strTempDistanceInput;
									boolIfComplete = true;
									break;
								}
							}

						} catch (NullPointerException e) {}

					// no match found
					} else if (boolCheckFlag2 == false) {
						JOptionPane.showMessageDialog(null, "No match found. Please try again", "Action Failed", JOptionPane.WARNING_MESSAGE);
					}

				}

			} // while(true)
		} // else

	} // doUpdate


	// Exit
	public static void doExit () {
		System.exit(0);
	}

	// main
	public static void main (String[] args) throws FileNotFoundException, IOException {

		// declaration and initialization
		Object objMenuList[] = {"Enroll A Marathoner", "View All Marathoners", "Erase File Contents", "Update a Marathoner Information", "Exit"};

		while (true) {
			try {
				Object objMenuInput = JOptionPane.showInputDialog(null, "Please select from the drop-down list:", "Main Menu", JOptionPane.QUESTION_MESSAGE, null, objMenuList, objMenuList[0]);

				// Enroll a Marathoner
				if (objMenuInput == objMenuList[0]) {
					doEnroll();

				// View All Marathoners
				} else if (objMenuInput == objMenuList[1]) {
					doView();

				// Erase File Contents
				} else if (objMenuInput == objMenuList[2]) {
					doClear();

				// Update a Marathoner Information
				} else if (objMenuInput == objMenuList[3]) {
					doUpdate();
				// Exit
				} else if (objMenuInput == objMenuList[4]) {
					doExit();
				}
			} catch (NullPointerException e) {}
		}



	} //public static void main (String[] args)

} //public class Sioson1