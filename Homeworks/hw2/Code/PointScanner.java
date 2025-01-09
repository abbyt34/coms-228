package edu.iastate.cs2280.hw2;

import java.io.File;

/**
 * 
 * @author Abby Taylor
 *
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 * 
 * @author Abby Taylor
 * @date 10/15/2024
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @param algo  Algorithm input
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		//assign the algo value to the sortingAlgorithm private instance
		this.sortingAlgorithm = algo;
		//assign the pts value to the points private instance
		this.points = new Point[pts.length];
		//if the array is null or zero values in length, throw an IllegalArgumentException
		if (pts == null || pts.length == 0) {
			throw new IllegalArgumentException("Invalid array of Points");
		}
		//interate through the whole array
		for (int i = 0; i < pts.length; i++) {
			//copy the pts array to the points array
			points[i] = pts[i];
		}
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @param  algo 
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		//intialize a counter to count the values in the file
		int numCount = 0;
		//assign the algo value to the sortingAlgorithm value
		this.sortingAlgorithm = algo;
		//create a new file object using the inputted file
		File file = new File(inputFileName);
		//if the file does not exist
		if (!file.exists()) {
			//throw a new FileNotFoundException
			throw new FileNotFoundException("File does not exist");
		}
		//create a new scanner to scan for number of values
		Scanner numscnr = new Scanner(file);
		//while the scanner finds another integer
		while (numscnr.hasNextInt()) {
			//iterate through all
			numscnr.nextInt();
			//increment numCount
			numCount += 1;
		}
		//close the numscnr for counting elements
		numscnr.close();
		//if the numCount is odd
		if (numCount % 2 != 0) {
			//throw a new InputMismatchException
			throw new InputMismatchException("There is an odd number of integers in the file");
		}
		//create a new Scanner object for the file
		Scanner scnr = new Scanner(file);
		//create a new array for the value of each pair
		points = new Point[numCount / 2];
		//for the length of the points array
		for (int i = 0; i < points.length; i++) {
			//find each x and y value for each pair
			int x = scnr.nextInt();
			int y = scnr.nextInt();
			//assign the pairs to the points array
			points[i] = new Point(x, y);
		}
		//close the Scanner object
		scnr.close();
	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 */
	public void scan()
	{
		//create a new AbstractSorter element
		AbstractSorter aSorter = null; 
		
		//for the sortingAlgorithm value, check each time to assign the AbstractSorter object
		if (sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);
		}
		else if (sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);
		}
		else if (sortingAlgorithm == Algorithm.QuickSort) {
			aSorter = new QuickSorter(points);
		}
		else if (sortingAlgorithm == Algorithm.SelectionSort) {
			aSorter = new SelectionSorter(points);
		}
		
        //a) call setComparator() with an argument 0 (for x values)
		aSorter.setComparator(0);
		//find the nano time before the sort
		long startTime = System.nanoTime();
        //b) call sort()
		aSorter.sort();
		//find the nano time after the sort
		long endTime = System.nanoTime();
		//c) use a new Point object to store the coordinates of the medianCoordinatePoint
		int medianX = aSorter.getMedian().getX();
		
        //a) call setComparator() with an argument 1 (for y values)
		aSorter.setComparator(1);
		//find the nanotime before the sort
		startTime += System.nanoTime();
		//b) call sort()
		aSorter.sort();
		//find the nanotime after the sort
		endTime += System.nanoTime();
		//c) use a new Point object to store the coordinates of the medianCoordinatePoint
		int medianY = aSorter.getMedian().getY();
		
		//d) set the medianCoordinatePoint reference to the object with the correct coordinates
		medianCoordinatePoint = new Point(medianX, medianY);
		
		//e) sum up the times spent on the two sorting rounds and set the instance variable scanTime
		scanTime = endTime - startTime;
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 * 
	 * @return output   String
	 */
	public String stats()
	{
		//create a new output string and intialize it
		String output = null;
		//get the sorting algorithm string 
		String algoName = sortingAlgorithm.toString();
		//find the size of the array
		int size = points.length;
		
		//if the algorithm is Selection or Insertion, make the string with three spaces between each variables
		if (algoName == "SelectionSort" || algoName == "InsertionSort") {
			//three spaces between each
			output = algoName + "   " + size + "   " + scanTime;
			
		}
		//if the algorithm is Merge or Quick, make the string with the same spacing as the other strings
		else if (algoName == "MergeSort" || algoName == "QuickSort")
		{
			//8 spaces between algoName and size and three between size and scanTime
			output = algoName + "       " + size + "   " + scanTime;
		}
		//return the output String
		return output;
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 * 
	 * @return MCP String
	 */
	@Override
	public String toString()
	{
		//find both x and y values of the medianCoordinatePoint
		int x = medianCoordinatePoint.getX();
		int y = medianCoordinatePoint.getY();
		//return the MCP in standard form
		return "MCP: (" + x + ", " + y + ")";
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		//create a new output file
		File outputFileName = new File("MCP_outputfile.txt");
		
		//try the FileWriter system
		try (FileWriter writer = new FileWriter(outputFileName)){
			//write the medianCoordinatePoint string to the file
			writer.write(medianCoordinatePoint.toString());
			//print that is turned out successful
			System.out.println("The file was written successfully.");
		}
		//catch the exception from the FileWriter if failed
		catch (IOException e) {
			//print the message from the failure
			e.getMessage();
		}
		//if the output file does not exist
		if (!outputFileName.exists()) {
			//throw a new FileNotFoundException
			throw new FileNotFoundException("File does not exist");
		}
		 
	}

	/**
	 * Helper getPoints() method in order to properly obtain the right Point array for testing and main use
	 * 
	 * @return points    Point array
	 */
	public Point[] getPoints() {
		//return points array
		return points;
	}	
	
}
