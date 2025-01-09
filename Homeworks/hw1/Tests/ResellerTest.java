package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * @author Abby Taylor
 * @date 9/20/2024
 * 
 * The ResellerTest class tests all the methods given in the Reseller class. 
 * This class tests the who() function, the next() function, and the Reseller constructor.
 */
class ResellerTest {

	/**
	 * identifyNextTest() tests the creation, finding, and identifying of certain cells 
	 * in a Town object.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void identifyNextTest() throws FileNotFoundException {
		//create a new Town object using the file reader
		Town t = new Town("ISP4x4.txt");
		
		//find a cell on the grid
		TownCell cell = t.grid[0][1];
		//test to see if the cell matches what the expected State output is
		assertEquals(State.RESELLER, cell.who(), "The cell should be a Reseller cell");
		
		//progress the given cell to the next transition
		TownCell nextCell = cell.next(t);
		//test to see if the next cell matches that the expected State output is
		assertEquals(State.EMPTY, nextCell.who(), "The cell should be a Empty cell");
	}
	
	/**
	 * constructorTest() tests the constructor class for Reseller and makes sure it is identified
	 * right in the grid
	 */
	@Test
	public void constructorTest() {
		//create a new empty 3x3 Town for testing
		Town t = new Town(3,3);
		
		//populate a Reseller cell in the empty grid
		t.grid[1][1] = new Reseller(t, 1, 1);
		//find the State of that same cell
		State actualState = t.grid[1][1].who();
		//test to see if the State of the cell matches what was meant to be populated in the cell
		assertEquals(State.RESELLER, actualState, "The state of this cell should be Reseller");
	}

}
