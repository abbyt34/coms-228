package edu.iastate.cs2280.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 *  
 * @author Abby Taylor  
 * @date 10/18/2024
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 *
 */

public class MergeSorter extends AbstractSorter
{
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "mergesort";
		
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		//use the mergeSortRec function to start the process
		mergeSortRec(points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		//create two half arrays for the left and right array
		Point[] left = new Point[pts.length / 2];
		Point[] right = new Point[pts.length - (pts.length / 2)];
		//if the pts array isn't the right length, return and end
		if (pts.length < 2) {
			return;
		}
		//find the mid point for the array
		int mid = pts.length / 2;
		//for the length of half the array
		for (int i = 0; i < mid; i++) {
			//add the points from the designated half to the half array
			left[i] = pts[i];
		}
		//for the length of the other half array
		for (int j = mid; j < pts.length; j++) {
			//add the points from the designated half to the half array
			right[j - mid] = pts[j];
		}
		//call mergeSortRec recursivley to the left and right half
		mergeSortRec(left);
		mergeSortRec(right);
		//call the merge function to the left right and full array
		merge(left, right, pts);
	}
	
	/**
	 * The merge function is used to compare each array and lengths in order to concatenate right and sort correctly
	 * 
	 * @param left
	 * @param right
	 * @param pts
	 */
	private static void merge (Point[] left, Point[] right, Point[] pts) {
		//intialize values for the arrays in order to properly account for everything
		int i = 0;
		int j = 0; 
		int k = 0;
		//while it is in the bounds of the left and right arrays
		while (i < left.length && j < right.length) {
			//compare the left index to the right index
			if(left[i].compareTo(right[j]) < 1) {
				//increment and assign values
				pts[k++] = left[i++];
			}
			else {
				//increment and assign values
				pts[k++] = right[j++];
			}
		}
		//while the i value is less than the left array
		while (i < left.length) {
			//increment and assign values
			pts[k++] = left[i++];
		}
		//while the j value is less than the right array
		while (j < right.length) {
			//increment and assign values
			pts[k++] = right[j++];
		}
	}
}
