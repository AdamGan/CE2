/* Name: Gan Wen Jie Adam
 * Student No: A0125495Y
 * Group: W10-2J
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
    private static final String MSG_EMPTY = "%!$s is empty";
    private static final String MSG_DISPLAY = "%1$s. %2$s";
    
    // global variables and objects
    private static PrintWriter writer = null;
    private static ArrayList<String> text = new ArrayList<>();
    private static String fileName = "";

    public static void main(String[] args) {

        if(args.length==0) {
            showToUser(MSG_NO_FILE_SPECIFIED);
            System.exit(1);
        }
        else {
            fileName = args[0];
            Scanner reader = new Scanner(System.in);
            try {
                writer = new PrintWriter(fileName);
            } 
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //welcome msg and continue to prompt for input
            showToUser(String.format(MSG_WELCOME, fileName));
            prompt(writer, reader);
        }
    }

    private static void showToUser(String text) {
        System.out.println(text);
    }
    
    //recursive method to prompt for user input until exit
    private static void prompt(PrintWriter writer, Scanner reader) {
        System.out.print(MSG_PROMPT);

        String input = reader.nextLine();
        String[] temp = input.split("\\s",2);

        //switch based on user command
        switch (temp[0]) {
        case "add": addText(temp[1]);
        break;
        case "display": display();
        break;
        case "delete":  int index = Integer.parseInt(temp[1]);
                        deleteText(index);
        break;
        case "clear": clear();
        break;
        case "exit": exit();
        break;
        default: invalid(reader);
        }
        prompt(writer, reader); //recursive prompt next command 
    }


    //unrecognized command
    private static void invalid(Scanner reader) {
        // TODO Auto-generated method stub
        showToUser(MSG_INVALID_COMMAND);
        prompt(writer, reader);
    }

    private static void exit() {
        // TODO Auto-generated method stub

        if(text.size() != 0) {
            for(int i = 0; i < text.size(); i++) {
                writer.println(text.get(i));
            }
            writer.close();
        }
        System.exit(1);
    }


    public static void clear() {
        // TODO Auto-generated method stub
        try {
            writer = new PrintWriter(fileName);
            showToUser(String.format(MSG_CLEARED, fileName));
        } 
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        text.clear();
    }

    //delete
    private static void deleteText(int index) {
        // TODO Auto-generated method stub

        if(index<1 || index-1>text.size()) {
            showToUser(MSG_INVALID_INDEX);
        }
        else {
            String tempRemoved = text.get(index-1);
            text.remove(index-1);
            showToUser(String.format(MSG_DELETED, fileName, tempRemoved));
        }
    }

    private static void display() {
        // TODO Auto-generated method stub
        if(text.size() == 0)
            showToUser(String.format(MSG_EMPTY, fileName));
        else {
            for(int i = 0; i < text.size(); i++) {
                showToUser(String.format(MSG_DISPLAY, i+1, text.get(i)));
            }
        }
    }

    private static void addText(String temp) {
        // TODO Auto-generated method stub
        text.add(temp);
        showToUser(String.format(MSG_ADDED, fileName, temp));
    }


}
