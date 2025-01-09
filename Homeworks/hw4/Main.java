package edu.iastate.cs2280.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main is the main function for the entire binary tree to read the file and output the decoded messages.
 * 
 * Uses command line inputs.
 * 
 * @author Abby Taylor
 * @date 12/8/2024
 */
public class Main {
	
	/**
	 * Main function to take in the file as a command line argument, read it, parse it correctly,
	 * and output the corrent messages using the MsgTree class
	 * 
	 * @param args
	 */
	public static void main (String[] args) {
		//if the command line length is more than 1
		if (args.length != 1) {
			//throw a new IllegalArgumentException since there can only be one input
			throw new IllegalArgumentException("There can only be one input.");
		}
		//intialize the filePath to the command argument
		String filePath = args[0];
		//try and catch for error finding
		try {
			//create a new file scanner
			Scanner fileScanner = new Scanner(new File(filePath));
			//create a new StringBuilder for the encoding string
			StringBuilder encodingStringBuild = new StringBuilder();
			//intlaize a encoded string message
			String encodedMessage = null;
			//while the file scanner has a next line
			while (fileScanner.hasNextLine()) {
				//intialize the current line
				String line = fileScanner.nextLine();
				//make sure the line is a encoded message using the helper method
				if (isEncoded(line)) {
					//intalize the encodedMessage with the current line
					encodedMessage = line;
					//break the loop
					break;
				}
				//if the line is not an encoded message
				else {
					//make sure the string builder is started
					if (encodingStringBuild.length() > 0) {
						//append the newline character
						encodingStringBuild.append("\n");	
					}
					//append the line
					encodingStringBuild.append(line);
				}
			}
			//if the encoded message is null
			if (encodedMessage == null) {
				//throw a new IllegalArgumentException since you need an encoded message to decode
				throw new IllegalArgumentException("Invalid file format: there is no encoded message");
			}
			//intialize the MsgTree varaible with the encoded string
			MsgTree tree = new MsgTree(encodingStringBuild.toString());
			//print the correct format for the codes
			System.out.println("character \tcode");
			System.out.println("---------------------");
			//print the codes using a MsgTree cast
			MsgTree.printCodes(tree, "");
			//line for organizing
			System.out.println("---------------------");
			//decode the message by casting to the MsgTree variable
			tree.decode(tree, encodedMessage);
			//close the file scanner
			fileScanner.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}
		
	}
	
	/**
	 * Make sure the line being read is the encoded string
	 * 
	 * @param line
	 * @return boolean true or false
	 */
	private static boolean isEncoded(String line) {
		//for each character in the character array in the line
		for (char c : line.toCharArray()) {
			//if the character is not 0 or 1
			if (c != '0' && c != '1') {
				//return false since it is not a encoded message
				return false;
			}
		}
		//return true since it is an encoded message
		return true;
	}
}
