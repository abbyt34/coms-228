package edu.iastate.cs2280.hw1;
import java.io.FileNotFoundException;

import java.util.Scanner;


/**
 * @author Abby Taylor
 * @date 9/21/2024
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * 
	 * @param tOld: old/current Town object
	 * @return tNew : new Town object
	 */
	public static Town updatePlain(Town tOld) {
		//create the same size grid based on the old town's dimensions
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		//iterate through the town grid
		for (int i = 0; i < tOld.getLength(); i++) {
			for (int j = 0; j < tOld.getWidth(); j++) {
				//update each cell individually using the .next(tNew) function
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew);
			}
		}
		//return the new town
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * 
	 * @param town
	 * @return int profit
	 */
	public static int getProfit(Town town) {
		//create a profit variable
		int profit = 0;
		//iterate through the town grid
		for (int i = 0; i < town.getLength(); i++) {
			for (int j = 0; j < town.getWidth(); j++) {
				//createa  temporary state for each grid cell
				State tempState = town.grid[i][j].who();
				//if the temporary state is a Casual type, add to the profit
				if (tempState == State.CASUAL) {
					//add one dollar per Casual type
					profit += 1;
				}
				//any other cell does not give any profit
				else {
					continue;
				}
			}
		}
		//return the total profit after the entire iteration
		return profit;
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {
		//create a scanner to read files and input
		Scanner scnr = new Scanner(System.in);
		//create a town variable in order to change and manipulate it
		Town t = null;
		//create a total profit variable to keep track of the total profit acquired through 12 months
		int totalProfit = 0;
		//ask for user input on how to populate the grid 1: from a file or 2: randomly with seed
		System.out.println("How to populate grid (type 1 or 2) : 1: from a file. 2: randomly with seed");
		String userOption = scnr.nextLine();
			
		//if user chooses 1: from a file
		if (userOption.equals("1")) {
			//prompt them to enter the specific file type
			System.out.println("Please enter file path: "); //should be "ISP4x4.txt"
			String filePath = scnr.nextLine();
			//try to reach the file
			try {
				//assigned the Town object to the file
				t = new Town(filePath);
			//if file not found, throw the FileNotFoundException message
			} catch (FileNotFoundException e) {
				e.getMessage();
			}
		}
		//if the user inputs option 2: randomly with seed
		else if (userOption.equals("2")) {
			//gather user input
			System.out.println("Provide rows, cols and seed integer seperated by sapces: ");
			//read the user input using the scanner
			int row = scnr.nextInt();
			int col = scnr.nextInt();
			int seed = scnr.nextInt();
			
			//create new Town using the user inputted row and col
			t = new Town(row, col);
			//assign a seed using the user inputted seed
			t.randomInit(seed);
		}
		//print to signify the start
		System.out.println("Start: ");
		//new line
		System.out.println("");
		//iterate through 12 months/calendar year
		for (int i = 0; i < 12; i++) {
			//if it is not the first month or the last, print the iteration number
			if (i != 0 && i != 11) {
				//print interation number
				System.out.println("After itr: " + i);
			}
			//print the grid
			System.out.println(t.toString());
		
			//get the profit of the current month iteration
			int profit = getProfit(t);
			//print the profit of the current month
			System.out.println("Profit: " + profit + "\n");
			//add to the totalProfit
			totalProfit += profit;
			//update the plain
			t = updatePlain(t);
			//continue for all 12 months
		}
		//new line
		System.out.println("");
		//calculate the max possible profit for the year
		int maxProfit = t.getLength() * t.getWidth();
		//find the average profit for the year
		double averageProfit = (totalProfit / 12.0);
		//find the profit utilization using the homework specifications 
		double profitUtilization = (averageProfit / maxProfit) * 100;
		//print the profit utilization using two decimal places
		System.out.printf("%.2f%%", profitUtilization);
		//close the scanner
		scnr.close();
	}
}
