package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * @author abbytaylormac
 * @date 9/20/2024
 * 
 * The CasualTest class tests each method given in the Casual class.
 * This class tests the next() function, the who() function, and the Casual constructor method
 */
class CasualTest {

	/**
	 * identifyNextTest creates a new Town object via file reading and throws a FileNotFoundExeception if the file isn't found
	 * For the Town object t, this method finds a Casual cell and checks it's state by using who() and then uses next() to update it and check
	 * it with who() again
	 * 
 	 * @throws FileNotFoundException
	 */
	@Test
	public void idenitfyNextTest() throws FileNotFoundException {
		//create a new Town object t using a the file reader method
		Town t = new Town("ISP4x4.txt");
		
		//find a cell 
		TownCell cell = t.grid[1][2];
		//evaluate if the current cells State matches State.CASUAL using who()
		assertEquals(State.CASUAL, cell.who(), "The cell should be an Casual cell type");
		
		//update the cell given into the next rotation
		TownCell nextCell = cell.next(t);
		//evaluate if the current cells State matches State.OUTAGE using who()
		assertEquals(State.OUTAGE, nextCell.who(), "The cell should be an Outage cell type");	
	}
	
	/*
	 * constructorTest() makes a new town and makes sure the Casual constructor works
	 */
	@Test
	public void constructorTest() {
		//creates a new empty grid
		Town t = new Town (3,3);
		
		//assigns a grid cell with a Casual cell
		t.grid[2][0] = new Casual(t, 2, 0);
		//finds the State of the given cell using who()
		State actualState = t.grid[2][0].who();
		//evaluates if the current cells State matches State.CASUAL 
		assertEquals(State.CASUAL, actualState, "The state should be Casual at this cell");
	}

}
