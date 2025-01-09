package edu.iastate.cs2280.hw2;

/**
 *  
 * @author Abby Taylor 
 * @date 10/15/2024
 * 
 * This class serves as the extension so all for formatting sorts, intalizing arrays, etc.
 *
 */

import java.util.Comparator;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later the sorted) sequence. 
 *
 */
public abstract class AbstractSorter
{
	
	protected Point[] points;    // array of points operated on by a sorting algorithm. 
	                             // stores ordered points after a call to sort(). 
	
	protected String algorithm = null; // "selection sort", "insertion sort", "mergesort", or
	                                   // "quicksort". Initialized by a subclass constructor.
		 
	protected Comparator<Point> pointComparator = null;  
	
	
	// Add other protected or private instance variables you may need. 
	

	protected AbstractSorter()
	{
		// No implementation needed. Provides a default super constructor to subclasses. 
		// Removable after implementing SelectionSorter, InsertionSorter, MergeSorter, and QuickSorter.
	}
	
	
	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException
	{
		//throw a IllegalArgumentException when the array is null or contains no elements in it.
		if (pts == null || pts.length == 0) {
			//throw new exception
			throw new IllegalArgumentException("Array of points is not valid.");
		}
		//create the points array
		points = new Point[pts.length];
		//loop through the inputted pts array and add to the privatr variable points array
		for (int i = 0; i < points.length ; i++) {
			points[i] = pts[i];
		}
	}

		
	
	
	

	/**
	 * Generates a comparator on the fly that compares by x-coordinate if order == 0, by y-coordinate
	 * if order == 1. Assign the 
     * comparator to the variable pointComparator. 
     *  
	 * 
	 * @param order  0   by x-coordinate 
	 * 				 1   by y-coordinate
	 * 			    
	 * 
	 * @throws IllegalArgumentException if order is less than 0 or greater than 1
	 *        
	 */
	public void setComparator(int order) throws IllegalArgumentException
	{
		//if the order is not valid
		if (order < 0 || order > 1) { 
			//throw new IllegalArgumentException
			throw new IllegalArgumentException("Order cannot be less than 0 or greater than 1");
		}
		//if the order is 0
		if (order == 0) {
			//set to X
			Point.setXorY(true);
			//create a new PointComparator for x
			pointComparator = new PointComparator();
		}
		//if the order is 1
		if (order == 1) {
			//set to Y
			Point.setXorY(false);
			//create a new PointComparator for y
			pointComparator = new PointComparator();
		}
	}

	

	/**
	 * Use the created pointComparator to conduct sorting.  
	 * 
	 * Should be protected. Made public for testing. 
	 */
	public abstract void sort(); 
	
	
	/**
	 * Obtain the point in the array points[] that has median index 
	 * 
	 * @return	median point 
	 */
	public Point getMedian()
	{
		return points[points.length/2]; 
	}
	
	
	/**
	 * Copys the array points[] onto the array pts[]. 
	 * 
	 * @param pts
	 */
	public void getPoints(Point[] pts)
	{
		//copies the inputted pts to points
		for (int i = 0; i < points.length; i++) {
			pts[i] = points[i];
		}
	}
	

	/**
	 * Swaps the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j)
	{
		//use a basic swap technique to swap the values
		Point temp = points[i];
		points[i] = points[j];
		points[j] = temp;
	}	
}
