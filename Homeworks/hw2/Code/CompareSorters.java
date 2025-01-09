package edu.iastate.cs2280.hw2;

/**
 *  
 * @author Abby Taylor 

 * @date 10/18/2024
 * 
 * This file is the main method to the idea behind the whole assignment, sorting and finding the median of each array via the 
 * sorting methods given (Selection, Insertion, Merge, Quick)
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		//intialize a new Scanner for the input
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
		System.out.println("keys: 1 (random integer)  2 (file input)  3 (exit)");
		//intialize the variables for keeping track of trials and inputted keys
		int trialNum = 0;
		int keyNum = 0;
		//intialize a points array
		Point[] points = null;
		//while the inputted keyNum is not 3 (which means exit)
		while (keyNum != 3) {
			//increment the trial number
			trialNum += 1;
			System.out.println("Trial " + trialNum + ": ");
			//read for the keyNum from the user input
			keyNum = scnr.nextInt();
			//a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
			//   of random points.
			if (keyNum == 1) {
				//new Random object
				Random rand = new Random();
				System.out.print("Enter number of random points: ");
				//read for the number of points wanted from the user input
				int numPts = scnr.nextInt();
				//intialize points for the generateRandomPoints generator
				points = generateRandomPoints(numPts, rand);
			}
			//if the inputted number is 2, start the file reading process
			//have to input the path src/points.txt
			else if (keyNum == 2) {
				System.out.println("Reading points from a file");
				System.out.print("File name: ");
				//read for the fileName string from the user input
				String fileName = scnr.next();
				//create a new PointScanner constructor for the file reader
				PointScanner fileScanner = new PointScanner(fileName, Algorithm.QuickSort);
				//use the getPoints() helper method to obtain an array and intialize points for it
				points = fileScanner.getPoints();
			}
			//if the inputted number is 3, the loop is exitted
			else if (keyNum == 3) {
				System.out.println("Exit...");
				break;
			}
			//if the input is anything else, there is an error and the loop continues
			else {
				System.out.println("Invalid input, try again");
				continue;
			}
			System.out.println();
			System.out.println("algorithm      size   time(ns)");
			System.out.println("----------------------------------");
			
			//b) Reassigns to the array scanners[] (declared below) the references to four new 
			//   PointScanner objects, which are created using four different values  
			//   of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort.
			PointScanner[] scanners = new PointScanner[4]; 
	        //a) Initialize the array scanners[].
			scanners[0] = new PointScanner(points, Algorithm.SelectionSort);
			scanners[1] = new PointScanner(points, Algorithm.InsertionSort);
			scanners[2] = new PointScanner(points, Algorithm.MergeSort);
			scanners[3] = new PointScanner(points, Algorithm.QuickSort);
			//b) Iterate through the array scanners[], and have every scanner call the scan() 
			//   method in the PointScanner class.
			for (PointScanner scanner : scanners) {
				//c) After all four scans are done for the input, print out the statistics table from
			    //   section 2.
				scanner.scan();
				System.out.println(scanner.stats());
			}
	
			System.out.println("----------------------------------");
		}
		//close the input scanner used
		scnr.close(); 		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 * @return points  	array of points
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		//if the number of points is less than 1, it is invalid
		if (numPts < 1) {
			//throws new IllegalArgumentException
			throw new IllegalArgumentException("Invalid number of points in the array");
		}
		//intialize a new points array with the number of random points inputted
		Point[] points = new Point[numPts];
		//for the length of the array
		for (int i = 0; i < numPts; i++) {
			//generate the x and y values using the Random object
			int x = rand.nextInt(101) - 50; //generates a number -50 to 50 using the given specification in the pdf
			int y = rand.nextInt(101) - 50; //generates a number -50 to 50 using the given specifiction in the pdf
			//assign the random x and y int values in the points array as a new Point
			points[i] = new Point(x, y); 
		}
		//return the random generatorated array
		return points;
	}
	
}
