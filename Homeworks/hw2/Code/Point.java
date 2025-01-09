package edu.iastate.cs2280.hw2;
/**
*  The Point class takes in the Comparable function in order to construct points, have getters and setters for x and y,
*  getters and setters for the xORy boolean, an overriden function for equals(), a compareTo() function for the Object given
*  and the Point constructed, and a toString() function to print a standard form point
* 
* @author Abby Taylor
* @date 10/11/2024
*
*/
public class Point implements Comparable<Point>
{
	private int x;
	private int y;
	
	public static boolean xORy;  // compare x coordinates if xORy == true and y coordinates otherwise
	                             // To set its value, use Point.xORy = true or false.
	
	public Point()  // default constructor
	{
		// x and y get default value 0
	}
	
	public Point(int x, int y)
	{
		// set the private variable x with the inputted value x
		this.x = x; 
		//set the private variable y with the inputted value y
		this.y = y;  
	}
	
	public Point(Point p) { // copy constructor
		//get the x value of the point
		x = p.getX();
		//get the y value of the point
		y = p.getY();
	}
	public int getX()  
	{
		//return x
		return x;
	}
	
	public int getY()
	{
		//return y
		return y;
	}
	
	/**
	 * Set the value of the static instance variable xORy.
	 * @param xORy
	 */
	public static void setXorY(boolean xORy)
	{
		//set the variable with the given boolean inserted into the function
		Point.xORy = xORy;
	}
	
	/**
	 * equals overrides the started Java equals method in order to compare the current Point
	 * to the inputted object
	 *
	 * @param Object obj
	 * @return boolean x == other.x && y == other.y;
	 * 		makes sure the objects coordinates match the points coordinates when cloned
	 */
	@Override
	public boolean equals(Object obj)
	{
		//if the object is null or does not match the given class, it is false and does not exist
		if (obj == null || obj.getClass() != this.getClass())
		{
			//return false
			return false;
		}
		//create a copy of the point
		Point other = (Point) obj;
		//return if they are the same point
		return x == other.x && y == other.y;  
	}
	/**
	 * Compare this point with a second point q depending on the value of the static variable xORy
	 *
	 * @param 	q
	 * @return  -1  if (xORy == true && (this.x < q.x || (this.x == q.x && this.y < q.y)))
	 *                || (xORy == false && (this.y < q.y || (this.y == q.y && this.x < q.x)))
	 * 		    0   if this.x == q.x && this.y == q.y) 
	 * 			1	otherwise
	 */
	public int compareTo(Point q)
	{
		//if the inputted value is greater then or equal to the given values in the class
		if ((xORy && (this.x < q.x || (this.x == q.x && this.y < q.y))) || (!xORy && (this.y < q.y || (this.y == q.y && this.x < q.x)))){
			//return -1
			return -1;
		}
		//if the values are the same
		else if (this.x == q.x && this.y == q.y) {
			//return 0
			return 0;
		}
		//if the inputted value is less than the given values in the class
		else {
			//return 1
			return 1;
		}
	}
	
	
	/**
	 * Output a point in the standard form (x, y).
	 *
	 * @return String (x, y) form of the point
	 */
	@Override
   public String toString()
	{
		//return the point coordinates in the standard form
		return "(" + x + ", " + y + ")";
	}
}
