package edu.iastate.cs2280.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author Abby Taylor   
 * @date 10/11/2024
 * 
 * The file InsertionSorter takes in an array and used the IntertionSort algorithm on it.
 *
 */

/**
 * 
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{ 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "insertion sort";
	}	

	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 */
	@Override 
	public void sort()
	{
		//find the length of the points array
		int n = points.length;
		
		//for every element in the array besides the first one
		for (int i = 1; i < n; i++) {
			//find the swap point
			Point key = points[i];
			//start the element j
			int j = i - 1;
			
			//while j is not out of boundes and key < points[j]
			while (j > -1 && pointComparator.compare(points[j], key) > 0) {
				//the point ahead becomes the point behind
				points[j + 1] = points[j];
				//deincrement j so it isn't an infinite loop
				--j;
			}
			//swap the ahead point for the key instance
			points[j + 1] = key;
		}
		
	}	
}
