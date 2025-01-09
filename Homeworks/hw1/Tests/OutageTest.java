package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * @author Abby Taylor
 * @date 9/20/2024
 * 
 * The OutageTest class is able to test each method that included in the Outage class. 
 * This class tests the who() function, the next() function, and the Outage constructor
 */
class OutageTest {

	/**
	 * identifyNextTest() tests the create, identifcation, and progression of certain cells in a Town 
	 * object
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void identifyNextTest() throws FileNotFoundException {
		//create a new Town based on the file reader function
		Town t = new Town("ISP4x4.txt");
		
		//find the cell that is in the first grid space
		TownCell cell = t.grid[0][0];
		//find the State of the cell in the first grid space and make sure it equals the Outage State type
		assertEquals(State.OUTAGE, cell.who(), "The state of this cell should be Outage");
		
		//use the next() function to progress the cell
		TownCell nextCell = cell.next(t);
		//find the State of the nextCell in the first grid and make sure it equals the Empty State tyoe
		assertEquals(State.EMPTY, nextCell.who(), "The state of this cell should be Empty");
	}
	
	/**
	 * constructorTest() tests the constructor function given in the Outage class
	 */
	@Test
	public void constructorTest() {
		//create a new 3x3 Town for testing
		Town t = new Town(3,3);
		
		//populate a new Outage cell using the constructor
		t.grid[2][1] = new Outage(t, 2, 1);
		//find the actual state in that same cell
		State actualState = t.grid[2][1].who();
		//make sure that state equals an Outage type
		assertEquals(State.OUTAGE, actualState, "The state of this cell should be Outage");
	}

}
