package edu.iastate.cs2280.hw4;
/**
* MsgTree is for intializing, decoding, and printing the inputted binary tree files
*
* @author Abby Taylor
* @date 12/8/2024
*/
public class MsgTree {
	//variable to store the current character
	public char payloadChar;
	//variable to store the left tree
	public MsgTree left;
	//variable to store the right tree
	public MsgTree right;
	
	//use for the char index of the tree string in recursive situation
	private static int staticCharIdx = 0;
	
	/**
	 * MsgTree takes in an encoding string where it will create two children trees
	 * to read from by reading the amount of characters that are ^ and
	 * seperating them.
	 *
	 * @param encodingString
	 */
	public MsgTree(String encodingString) {
		//if the encodingString is null, empty, or if it is the length of the staticCharIdx variable
		if (encodingString == null || encodingString.isEmpty() || staticCharIdx >= encodingString.length()) {
			//return to end the function
			return;
		}
		//intialize the payloadChar with the character at the staticCharIdx in the encodingString
		this.payloadChar = encodingString.charAt(staticCharIdx);
		//increment the staticCharIdx
		staticCharIdx++;
		//if the payloadChar is '^'
		if (this.payloadChar == '^') {
			//create the left and right trees recursivley with the encodingString
			this.left = new MsgTree(encodingString);
			this.right = new MsgTree(encodingString);
		}
		//if the variable is anything else
		else {
			//the tree is null
			this.left = null;
			this.right = null;
		}
	}
	
	/**
	 * Constructor for the binary tree with a payloadChar and null children
	 *
	 * @param payloadChar
	 */
	public MsgTree(char payloadChar) {
		//intialize the payloadChar
		this.payloadChar = payloadChar;
		//create the null children
		this.left = null;
		this.right = null;
	}
	
	/**
	 * Prints the variables and their binary codes
	 *
	 * @param root
	 * @param code
	 */
	public static void printCodes(MsgTree root, String code) {
		//if the root is null
		if (root == null) {
			//return to end the function
			return;
		}
		
		//if the root is not '^'
		if (root.payloadChar != '^') {
			//if the root is a newline character
			if (root.payloadChar == '\n') {
				//print out the newline character and it's binary code
				System.out.println("\\n \t\t" + code);
			}
			//if the root is a space
			else if (root.payloadChar == ' ') {
				//print out "space" and it's binary code
				System.out.println("space \t\t" + code);
			}
			//if it is neither character
			else {
				//print out the character and it's binary code
				System.out.println(root.payloadChar + " \t\t" + code);
			}
		}
		//recursivley call the printCodes function to both the left and right children
		printCodes(root.left, code + "0");
		printCodes(root.right, code + "1");
	}
	
	/**
	 * Decodes the message based on the tree and message given and prints it to the console.
	 *
	 * Searches the tree for the character and matches it to the current position in the message.
	 *
	 * @param codes
	 * @param msg
	 */
	public void decode(MsgTree codes, String msg) {
		//if the tree is null
		if (codes == null) {
			//return to end the function
			return;
		}
		//print the MESSAGE
		System.out.println("MESSAGE:");
		//create a new StringBuilder to append the decoded message
		StringBuilder decodedMessage = new StringBuilder();
		//intialize a temp variable for the tree
		MsgTree cd = codes;
		//for the length of the message
		for (int i = 0; i < msg.length(); i++) {
			//find the character at the given index
			char c = msg.charAt(i);
			//if the current character is '0', then it goes to the left child
			//if the current character is '1', then it goes to the right child
			cd = (c == '0' ? cd.left : cd.right);
			//if the left and right child are null
			if (cd.left == null || cd.right == null) {
				//append the payloadChar for the current tree
				decodedMessage.append(cd.payloadChar);
				//intialize the cd to the original tree
				cd = codes;
			}
		}
		//print the decoded message
		System.out.println(decodedMessage.toString());
		//line for spacing and organization
		System.out.println("---------------------------------------------------------------------------");
		//print the statistics for the current message
		statistics(msg, decodedMessage.toString());
		
	}
	
	/**
	 * Print the statistics for the encode and decode strings of the current message.
	 *
	 * For extra credit.
	 *
	 * @param encodeStr
	 * @param decodeStr
	 */
	private static void statistics(String encodeStr, String decodeStr) {
		//print out the message STATISTICS
		System.out.println("STATISTICS:");
		//print our the average bits per character
		System.out.printf("Avg bits/char: \t%.1f%n", encodeStr.length() / (double) decodeStr.length());
		//print out the total characters
		System.out.println("Total Characters: \t" + encodeStr.length());
		//print out the space savings percent per bit
		System.out.printf("Space savings: \t%.1f%%%n", (1 - (encodeStr.length() / (double)(decodeStr.length() * 16))) * 100);
	}
	
}
