package edu.iastate.cs2280.hw2;

import java.util.Comparator;

/**
 * @author Abby Taylor 
 * @date 10/14/2024
 */

/**
 * This class is made to meet the specifications of the pdf to make Comparator class
 * Only one method is present, compare()
 */
public class PointComparator implements Comparator<Point>{

	/**
	 * @param p, q     both point objects
	 * @return p.compareTo(q)     int for the compareTo class
	 */
	@Override
	public int compare(Point p, Point q) {
		//call the compareTo function
		return p.compareTo(q);
	}
}
