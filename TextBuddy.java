/* Name: Gan Wen Jie Adam
 * Student No: A0125495Y
 * Group: W10-4J
 * Submission: CE1
 */

import java.io.*;
import java.util.*;

public class TextBuddy {

	private static final String MSG_NO_FILE_SPECIFIED = "No filename specified";
	private static final String MSG_WELCOME = "Welcome to TextBuddy. %1$s is ready for use";
	private static final String MSG_PROMPT = "command: ";
	private static final String MSG_INVALID_COMMAND = "invalid command";
	private static final String MSG_INVALID_INDEX = "Invalid index";
	private static final String MSG_DELETED = "deleted from %1$s: \"%2$s\"";
	private static final String MSG_ADDED = "added to %1$s: \"%2$s\"";
	private static final String MSG_CLEARED = "all content deleted from %1$s";
	private static final String MSG_EMPTY = "%1$s is empty";
	private static final String MSG_DISPLAY = "%1$s. %2$s";
	
	// global variables and objects
	private static PrintWriter writer = null;
	private ArrayList<String> text = new ArrayList<String>();
	private static String fileName = "";
	Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		TextBuddy myTextBuddy = new TextBuddy();
		
		myTextBuddy.run(args);
	}
	
	//start of program, creation of file and reading of input
	void run(String[] args) {
		if(args.length==0) {
			showToUser(MSG_NO_FILE_SPECIFIED);
			System.exit(1);
		}
		else {
			Scanner reader = new Scanner(System.in);
			createFile(args);

			//welcome msg and continue to prompt for input
			showToUser(String.format(MSG_WELCOME, fileName));
			promptInput(writer, reader);
		}
	}
	
	//creates file based on name passed in args[0]
	//returns true if file creation is successful and false if not
	static boolean createFile(String[] args) {
		boolean success = false;
		fileName = args[0];
		
		try {
			writer = new PrintWriter(fileName);
			success = true;
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	//simple printer
	void showToUser(String text) {
		System.out.println(text);
	}
	
	//recursive method to prompt for user input until exit
	void promptInput(PrintWriter writer, Scanner reader) {
		System.out.print(MSG_PROMPT);

		String input = reader.nextLine();
		String[] temp = input.split("\\s",2);

		//switch based on user command
		switch (temp[0]) {
		case "add": 	text = addText(text, temp[1]);
						break;
						
		case "display": display(text);
						break;
						
		case "delete":	int index = Integer.parseInt(temp[1]);
						text = deleteText(text,index);
						break;
						
		case "clear": 	text = clear(text);
						break;
						
		case "exit": 	exit();
						break;
						
		case "sort":	text = sortInput(text);
						break;
						
		default: 		invalid(reader);
		}
		//recursive prompt next user input
		promptInput(writer, reader); 
	}


	//input is an unrecognized command
	//display error and prompt for input again
	void invalid(Scanner reader) {
		showToUser(MSG_INVALID_COMMAND);
		promptInput(writer, reader);
	}
	
	
	void exit() {

		if(text.size() != 0) {
			for(int i = 0; i < text.size(); i++) {
				writer.println(text.get(i));
			}
			writer.close();
		}
		System.exit(1);
	}

	//clear all existing inputs and return the arraylist
	public ArrayList<String> clear(ArrayList<String> myText) {
		
		try {
			writer = new PrintWriter(fileName);
			showToUser(String.format(MSG_CLEARED, fileName));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		myText.clear();
		
		return myText;
	}

	//delete based on line index from user and returns new arraylist
	public ArrayList<String> deleteText(ArrayList<String> myText, int index) {
		
		if(index<1 || index-1>text.size()) {
			showToUser(MSG_INVALID_INDEX);
		}
		else {
			String tempRemoved = text.get(index-1);
			myText.remove(index-1);
			showToUser(String.format(MSG_DELETED, fileName, tempRemoved));
		}
		
		return myText;
	}
	
	//displays the content of arraylist
	//prints error msg if arraylist is empty
	public boolean display(ArrayList<String> myText) {
		boolean isEmpty = false;
		
		if(myText.size() == 0) {
			showToUser(String.format(MSG_EMPTY, fileName));
			isEmpty = true;
		}
		else {
			for(int i = 0; i < myText.size(); i++) {
				showToUser(String.format(MSG_DISPLAY, i+1, myText.get(i)));
			}
		}
		return isEmpty;
	}

	//adds a user input string into the arraylist
	public ArrayList<String> addText(ArrayList<String> myText, String temp) {
		myText.add(temp);
		showToUser(String.format(MSG_ADDED, fileName, temp));
		return myText;
	}
	
	public ArrayList<String> sortInput(ArrayList<String> myText) {
		Collections.sort(myText);
		return myText;
	}
	
}
